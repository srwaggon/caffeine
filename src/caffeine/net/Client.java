package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.GUI;
import caffeine.world.Map;

public class Client {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected GUI gui;
  protected Connection host;
  protected NetworkInputHandler input;

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).run();
  }

  /* Constructor */
  public Client(String ip, int port) {
    host = new Connection(ip, port);
    input = new NetworkInputHandler(host);
    gui = new GUI("Caffeine Client");
    gui.addInputHandler(input);
  }

  public void run() {
    new Thread(gui).start();
    try {
      String query = "Hello Server!";
      String response = "";
      Scanner scan = new Scanner(System.in);
      while (host.isConnected()) {
        query = scan.nextLine();
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
    System.out.println(response);
    while (lineParser.hasNext()) {
      String strmap = lineParser.next();
      String mapID = lineParser.next();

      String mapData = lineParser.nextLine();
      System.out.println(mapData);

      Map map = new Map(mapData);
      System.out.println(map);

      gui.render(map);
    }
  }
}
