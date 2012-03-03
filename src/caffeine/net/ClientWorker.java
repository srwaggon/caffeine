package caffeine.net;

import java.net.Socket;

import caffeine.Game;

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
      while (client.isConnected()) {
        response = "";
        input = client.read();
        client.send(response);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {
    }
  }
}
