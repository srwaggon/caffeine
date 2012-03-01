package caffeine;

import java.net.Socket;

import caffeine.entity.Entity;
import caffeine.world.Map;

class ClientWorker extends Thread {
  private final Connection client;
  
  ClientWorker(Socket client) {
    this.client = new Connection(client);
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");
  }
  
  public void run() {
    String input;
    String response = "";
    client.send("Hello client");
    Map m = Server.instance().world().get(0);
    
    try {
      while (client.isConnected()) {
        response = "";
        input = client.read();
        for (Entity e : m.entities()) {
          response += e.toString() + " ";
        }
        client.send(response);
        Thread.sleep(10);
      }
    } catch (Exception e) {
    }
  }
}
