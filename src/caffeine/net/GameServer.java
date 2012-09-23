package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import caffeine.Caffeine;

public class GameServer extends Thread {
  private final Caffeine game;
  private ServerSocket socket = null;
  private final int port;
  private final List<ClientWorker> clients = new ArrayList<ClientWorker>();

  public GameServer(Caffeine _game, int port) {
    game = _game;
    this.port = port;
    try {
      socket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Could not listen on port " + port);
      System.exit(-1);
    }
  }

  public void run() {
    try {
      while (true) {
        addClientWorker(socket.accept());
        Thread.sleep(1000);
      }
    } catch (IOException e) {
      System.out.println("Accept failed: " + port);
      System.exit(-1);
    } catch (InterruptedException e) { e.printStackTrace(); }
  }
  
  public synchronized void handle(String msg, byte id) {
    //System.out.println(msg + " received from " + id);
    broadcast(id + ": " + msg);
  }
  
  public void broadcast(String msg){
    for(int i = 0; i < clients.size(); i++){
      System.out.println("Broadcasting to " + clients.size());
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
