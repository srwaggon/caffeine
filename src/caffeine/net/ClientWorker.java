package caffeine.net;

import java.net.Socket;
import java.util.Scanner;

import caffeine.Caffeine;
import caffeine.Game;

public class ClientWorker extends Thread {
  protected final Connection client;
  protected Caffeine game;
  
  public ClientWorker(Game g, Socket client) {
    game = (Caffeine) g;
    this.client = new Connection(client);
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");
  }
  
  public Game game() {
    return game;
  }
  
  public void run() {
    try {
      String query = "";
      String reply = "";
      while (client.isConnected()) {
        query = client.read();
        reply = processQuery(query);
        client.send(reply);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {
    }
  }
  
  public String processQuery(String query) {
    Scanner reader = new Scanner(query);
    String result = "";
    
    if (reader.hasNext()) {
      String queryType = reader.next();
      
      if (queryType.equals("world")) {
        result = game.world().toString();
        
      } else if (queryType.equals("map")) {
        result = game.world().get(reader.nextInt()).toString();
        
      }
      
    }
    
    return result;
  }
}
