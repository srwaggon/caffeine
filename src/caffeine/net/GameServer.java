package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import caffeine.Game;
import caffeine.net.msg.MsgHandler;

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


  public void addClientWorker(Socket client){
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting.");

    GameServerWorker worker = new GameServerWorker(this, client);
    clients.add(worker);
    worker.start();

    broadcast(game.getMap(0).toString());
  }



  public synchronized void handle(String msg) {
    MsgHandler.handle(msg, game);
    String reply = game.getMap(0).toString();
    broadcast(reply);
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
