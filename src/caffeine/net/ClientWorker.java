package caffeine.net;

import java.net.Socket;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.world.Map;

public class ClientWorker extends Thread {
  private final Connection client;
  String input;
  String response = "";
  Map m;
  
  public ClientWorker(Socket client) {
    this.client = new Connection(client);
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");
  }
  
  public void run() {
    try {
      client.send("Hello client");
      m = Game.instance().world().get(0);
      while (client.isConnected()) {
        response = "";
        input = client.read();
        for (Entity e : m.entities()) {
          response += e.toString() + " ";
        }
        client.send(response);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {
    }
  }
}
