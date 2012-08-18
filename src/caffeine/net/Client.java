package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.world.World;

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
    gui.addInputListener(input);
  }

  public void run() {
    try {
      String query = "get map 0";

      String response = "";
      Scanner scan = new Scanner(System.in);
      while (host.isConnected()) {
        host.send(query);
        processServerResponse(response);
        response = host.read();
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
      World world = new World();
      //world.addMap(mapData);
      //Map map = world.getMap(0);
      //System.out.println(map);

    }
  }
}
