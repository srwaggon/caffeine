package caffeine.net;

import java.net.Socket;

import caffeine.Game;
import caffeine.entity.Entity;

public class ClientWorker extends Thread {
  private final Connection client;
  String input;
  String response = "";
  Game game;
  
  public ClientWorker(Game g, Socket client) {
    game = g;
    this.client = new Connection(client);
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");
  }
  
  public void run() {
    try {
      client.send("Hello client");
      while (client.isConnected()) {
        response = "";
        input = client.read();
        for (Entity e : game.world().get(0).entities()) {
          response += e.toString() + " ";
        }
        client.send(response);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {
    }
  }
}
