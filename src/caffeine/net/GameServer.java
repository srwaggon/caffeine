package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.entity.PlayerEntity;
import caffeine.net.accounts.PlayerAccount;
import caffeine.net.packet.EventPacket;
import caffeine.net.packet.MapPacket;
import caffeine.net.packet.Packet;
import caffeine.world.tile.Tile;

public class GameServer extends Thread implements MapListener{
  protected final int port;
  protected Game game;
  protected ServerSocket socket = null;
  protected ArrayList<GameServerWorker> workers = new ArrayList<GameServerWorker>();

  public static void main(String args[]) {
    Game game = new Game();
    game.start();
    GameServer gs = new GameServer(game, 4444);
    gs.run();
  }

  public GameServer(Game game, int port) {
    this.port = port;
    this.game = game;

    try {
      socket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Could not listen on port " + port);
      System.exit(-1);
    }
    game.getMap(0).addMapListener(this);
  }

  public void run() {
    while (true) {
      try {
        System.out.print("Awaiting connection...");
        acceptConnection(socket.accept());
        Thread.sleep(100);
      } catch (IOException ioe) {
        System.out.println("Accept failed: " + port);
        ioe.printStackTrace();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }

  public void acceptConnection(Socket socket) {
    System.out.println(socket.getInetAddress().toString() + " connecting.");
    Connection client = new Connection(socket);
    GameServerWorker worker;
    try {
      worker = new GameServerWorker(this, client);
      worker.start();
    } catch (IOException e) {
      client.send(Packet.LoginFailure);
      e.printStackTrace();
    }

  }

  public synchronized void handle(PlayerAccount player, Packet packet) {
    if (packet.getCode() == Packet.Code.EVENT) {
      System.out.println(game.getEntities(0));
      System.out.println(packet);
      EventPacket event = (EventPacket) packet;
      event.getEvent().apply();
    }

    //System.out.println("Packet received: " + packet);
    broadcast(packet);
  }

  public void broadcast(Packet packet) {
    for (GameServerWorker worker : workers) {
      worker.send(packet);
    }
  }

  protected void finalize() {
    try {
      socket.close();
      super.finalize();
    } catch (IOException e) {
      System.out.println("Could not close.");
      System.exit(-1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public synchronized void addWorker(GameServerWorker worker) {
    // Handle the newly connected player.
    PlayerAccount player = worker.getPlayer();

    // : Add his piece to the game.
    PlayerEntity entity = player.getEntity();
    System.out.println(entity.ID);
    game.addEntity(entity, entity.getMapID());
    workers.add(worker);
    worker.client.send(new MapPacket(entity.getMap()));
  }

  public synchronized void removeWorker(GameServerWorker worker) {
    PlayerAccount player  = worker.getPlayer();
    PlayerAccount.savePlayer(player);
    player.getEntity().remove();
    workers.remove(worker);
  }

  public void onAddEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }

  public void onRemoveEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }

  public void onTileChange(Tile tile) {
    ;
  }



}
