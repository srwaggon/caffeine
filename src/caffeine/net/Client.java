package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.GUI;

public class Client {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected GUI gui = new GUI("Caffeine Game Client");
  protected Connection host;
  
  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).run();
  }
  
  /* Constructor */
  public Client(String ip, int port) {
    host = new Connection(ip, port);
    new Thread(gui).start();
  }
  
  public void run() {
    try {
      String query = "Hello Server.";
      String response = "";
      while (host.isConnected()) {
        host.send(query);
        response = host.read();
        processServerResponse(response);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {
    }
  }
  
  public void processServerResponse(String response) {
    Scanner lineParser = new Scanner(response);
    String next;
    
    while (lineParser.hasNext()) {
      next = lineParser.next();
    }
  }
}
