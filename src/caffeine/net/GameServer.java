package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.entity.Mob;
import caffeine.entity.PlayerEntity;
import caffeine.net.accounts.PlayerAccount;
import caffeine.net.packet.ActionPacket;
import caffeine.net.packet.ErrorPacket;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MapPacket;
import caffeine.net.packet.MovePacket;
import caffeine.net.packet.Packet;
import caffeine.world.tile.Tile;

public class GameServer extends Thread implements MapListener {
  private final int port;
  private final Game game;
  private ServerSocket socket = null;
  private final HashMap<String, GameServerWorker> clients = new HashMap<String, GameServerWorker>();
  
  public static void main(String args[]) {
    Game game = new Game();
    GameServer gs = new GameServer(game, 4444);
    game.start();
    gs.start();
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
  
  @Override
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
  
  /**
   * Receives a socket representing a client connection and wraps it in a
   * connection before attempting to authenticate it as a valid login packet. If
   * the login is successful, a GameServerWorker is requested and assigned to
   * handle incoming data from the client.
   * 
   * @param socket
   */
  public void acceptConnection(Socket socket) {
    System.out.println(socket.getInetAddress().toString() + " connecting.");
    Connection client = new Connection(socket);
    
    Packet packet = null;
    try {
      packet = client.readPacket();
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return;
    }
    
    // authenticate primary packet is login or new account.
    if (packet.getCode() != Packet.Code.LOGIN) {
      client.send(new ErrorPacket("Bad login."));
      return;
    }
    
    // check player username / password authenticity.
    LoginPacket loginPacket = (LoginPacket) packet;
    PlayerAccount account = PlayerAccount.loadPlayer(loginPacket.USERNAME);
    if (!account.authenticate(loginPacket.PASSWORD)) {
      client.send(new ErrorPacket("Bad username/password."));
      return;
    }
    
    // check if not already online.
    if (clients.get(account.getUsername()) != null) {
      client.send(new ErrorPacket("Account already connected."));
      return;
    }
    
    // accept connection
    GameServerWorker worker = requestWorker();
    worker.handleConnection(client, account);
    assignClient(worker, account);
    worker.start();
  }
  
  public synchronized void handle(PlayerAccount player, Packet packet) {
    switch (packet.getCode()) {
      case MOVE:
        MovePacket move = (MovePacket) packet;
        game.getEntity(move.USERNAME).move(move.DIR);
        break;
      case JUMP:
        ActionPacket jump = (ActionPacket) packet;
        game.getEntity(jump.USERNAME).jump();
        break;
      case USERIGHT:
        ActionPacket use = (ActionPacket) packet;
        Mob m = ((Mob) game.getEntity(use.USERNAME));
        m.useRightHand();
      default:
        break;
    }
    broadcast(packet);
  }
  
  public void broadcast(Packet packet) {
    for (GameServerWorker client : clients.values()) {
      client.send(packet);
    }
  }
  
  @Override
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
  
  public GameServerWorker requestWorker() {
    GameServerWorker worker = new GameServerWorker(this);
    return worker;
  }
  
  public synchronized void assignClient(GameServerWorker worker,
      PlayerAccount account) {
    
    // Add account's avatar to the game.
    PlayerEntity entity = account.getEntity();
    System.out.println(entity.ID + " added to game.");
    game.addEntity(entity, entity.getMapID());
    worker.client.send(new MapPacket(entity.getMap()));
    
    // track worker by account
    clients.put(account.getUsername(), worker);
    
  }
  
  public synchronized void removeAccount(PlayerAccount account) {
    PlayerAccount.savePlayer(account);
    account.getEntity().remove();
    clients.remove(account.getUsername());
  }
  
  @Override
  public void onAddEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }
  
  @Override
  public void onRemoveEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }
  
  @Override
  public void onTileChange(Tile tile) {
    ;
  }
}
