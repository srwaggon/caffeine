package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.world.Map;

public class Client {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Map map;
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

    while (host.isConnected()) {
      handle(host.read());
      map.renderBackground(gui.screen);
      gui.screen.render();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  public void handle(String msg) {
    System.out.println(msg);
    Scanner scanner = new Scanner(msg);
    String word;
    while(scanner.hasNext()) {
      word = scanner.next();

      if (word.equals("map")){
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int ts = scanner.nextInt();
        String mapData = scanner.next();
        map = new Map(w, h, ts, mapData);
      }

      if (word.equals("entity")){
        int id = scanner.nextInt();
        int mapid = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        //map.addEntity(new Entity(id, map, x, y));
      }
    }
    //host.send("Thanks for the map.");
  }

  public void finalize(){
    host.disconnect();
  }
}
