package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.GUI;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;

public class Client extends Thread {
  /* Engine Fields */
  private World realm = null;
  private final HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected GUI gui = null;
  private final Connection host;
  
  /* MAIN METHOD */
  public static void main(String[] args) {
    Client c = new Client();
    c.run();
  }
  
  /* Constructor */
  public Client() {
    host = new Connection("127.0.0.1");
    
    realm = new World();
    Map map = new Map();
    realm.add(map);
    gui = new GUI(map);
    gui.setTitle("Caffeine Client");
    new Thread(gui).start();
  }
  
  public void run() {
    try {
      host.send("Hello server");
      while (host.isConnected()) {
        String input = host.read();
        processServerResponse(input);
        String response = "Ok.";
        host.send(response);
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
      
      /* Handle the input */
      if (next.equals("eot")) {
        /* End of transmission */
        host.disconnect();
        break;
        
      } else if (next.equals("map")) {
        Map map = new Map(lineParser.nextLine());
        
        gui.getContentPane().setCurrentMap(map);
      } else if (next.equals("entity")) {
        /* Update entity */
        int entityID = lineParser.nextInt();
        int mapID = lineParser.nextInt();
        int x = lineParser.nextInt();
        int y = lineParser.nextInt();
        
        if (entities.containsKey(entityID)) {
          entities.get(entityID).loc().set(mapID, x, y);
        } else {
          Entity e = new Entity(new Location(mapID, x, y));
          realm.get(0).add(e);
          entities.put(entityID, e);
        }
      } else {
        lineParser.nextLine();
      }
    }
  }
}
