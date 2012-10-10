package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import caffeine.Game;
import caffeine.Player;
import caffeine.entity.PlayerEntity;
import caffeine.net.msg.MsgHandler;
import caffeine.world.Map;

public class GameServer extends Thread {
  protected final int port;
  protected Game game;
  protected ServerSocket socket = null;
  protected final List<GameServerWorker> clients = new ArrayList<GameServerWorker>();

  public static void main(String args[]) {
    Game game = new Game();
    game.start();
    GameServer gs = new GameServer(game, 4444);
    gs.run();
  }

  public GameServer(Game _game, int _port) {
    port = _port;
    game = _game;

    try {
      socket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Could not listen on port " + port);
      System.exit(-1);
    }
  }


  public void run() {
    while (true) {
      try {
        System.out.print("Awaiting connection...");
        addClientWorker(socket.accept());
      } catch (IOException e) {
        System.out.println("Accept failed: " + port);
      }
    }
  }


  public void addClientWorker(Socket socket) {
    System.out.println(socket.getInetAddress().toString() + " connecting.");
    Connection client = new Connection(socket);

    // Load the connecting player's account.
    String account = client.read();
    Player player = Player.loadPlayer(account);
    client.send("" + player.ID);

    // Add his piece to the game.
    PlayerEntity entity = player.getEntity();
    game.addEntity(entity, entity.getMapID());


    Map map = game.getMap(entity.getMapID());
    client.send(map.toString());

    // Create a liason.
    GameServerWorker worker = new GameServerWorker(this, client, player);
    clients.add( worker );
    worker.start();
  }


  public synchronized void handle(String msg) {
    MsgHandler.handle(msg, game);
  }


  public void broadcast(String msg){
    for(int i = 0; i < clients.size(); i++){
      clients.get(i).send(msg);
    }
  }


  public List<GameServerWorker> clients() {
    return clients;
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

  public synchronized void remove(GameServerWorker client) {
    clients.remove(client);
  }

}
