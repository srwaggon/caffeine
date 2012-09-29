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
  protected final List<ClientWorker> clients = new ArrayList<ClientWorker>();

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
        addClientWorker(socket.accept());
      } catch (IOException e) {
        System.out.println("Accept failed: " + port);
        e.printStackTrace();
        System.exit(-1);
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) { e.printStackTrace(); }
    }
  }


  public synchronized void handle(String msg, byte id) {
    MsgHandler.handle(id + " " + msg, game);
    String reply = game.getDefaultMap().toString();
    System.out.println(reply);
    broadcast(reply);
  }


  public void broadcast(String msg){
    for(int i = 0; i < clients.size(); i++){
      clients.get(i).send(msg);
    }
  }


  public void addClientWorker(Socket client){
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");

    ClientWorker worker = new ClientWorker(this, client);
    clients.add(worker);
    Thread t = new Thread(worker);
    t.start();
  }

  public List<ClientWorker> clients() {
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

  public synchronized void remove(ClientWorker client) {
    clients.remove(client);
  }

}
