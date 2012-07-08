package caffeine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import caffeine.Caffeine;
import caffeine.Player;

public class GameServer extends Thread {
  private final Caffeine game;
  private ServerSocket socket = null;
  private final int port;
  private final List<ClientWorker> clients = new ArrayList<ClientWorker>();

  public GameServer(Caffeine g, int port) {
    game = g;
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
      ClientWorker w;
      while (true) {
        w = new ClientWorker(game, socket.accept());
        Thread t = new Thread(w);
        clients.add(w);
        game.addPlayer(new Player(w));
        t.start();
        Thread.sleep(1000);
      }
    } catch (IOException e) {
      System.out.println("Accept failed: " + port);
      System.exit(-1);
    } catch (InterruptedException e) {
    }
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
}
