package caffeine;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.view.GUI;
import caffeine.view.Screen;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the
 * mechanics, but are broken down into smaller components.
 * 
 * @author Fnar
 */
public final class Server extends Thread {
  /* Engine Fields */
  private final static Server      INSTANCE = new Server();                 // Instance
  private final World              world    = new World();                  // Space
  private final Clock              clock    = new Clock();                  // Time
  private GUI                      gui      = null;
  
  /* Networking Fields */
  private ServerSocket             server   = null;
  private final PrintWriter        out      = null;
  private final List<ClientWorker> clients  = new ArrayList<ClientWorker>();
  
  /* Main method */
  public static void main(String args[]) {
    
    // Get the game
    final Server game = Server.instance();
    
    // Add some data: A world, some entities
    Map map = new Map();
    game.world().add(map);
    game.createGUI();
    
    Location l = new Location(0, 48, 48);
    Player p1 = new Player(l, game.gui().getInteractions());
    map.add(p1);
    
    Screen s = game.gui().getContentPane();
    s.camera().focusOn(p1);
    
    // Start the game
    game.run();
  }
  
  /* CONSTRUCTORS */
  private Server() {
    initSockets();
    clock.add(new TimerTask() {
      public void run() {
        world.tick();
      }
    });
  }
  
  public void run() {
    listenSocket();
  }
  
  /* ACCESSORS */
  public List<Entity> entities(int mapID) {
    return world.get(mapID).entities();
  }
  
  public List<ClientWorker> clients() {
    return clients;
  }
  
  public GUI gui() {
    return gui;
  }
  
  public static Server instance() {
    return Server.INSTANCE;
  }
  
  public World world() {
    return world;
  }
  
  /* HELPERS */
  private void createGUI() {
    if (gui == null) {
      gui = new GUI(world.get(0));
      gui.setTitle("Caffeine Server");
      new Thread(gui).start();
    }
  }
  
  private void initSockets() {
    try {
      server = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port 4444");
      System.exit(-1);
    }
  }
  
  private void listenSocket() {
    while (true) {
      ClientWorker w;
      try {
        w = new ClientWorker(server.accept());
        Thread t = new Thread(w);
        clients.add(w);
        t.start();
        Thread.sleep(100);
      } catch (IOException e) {
        System.out.println("Accept failed: 4444");
        System.exit(-1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  protected void finalize() {
    // Clean up
    try {
      out.close();
      server.close();
      super.finalize();
    } catch (IOException e) {
      System.out.println("Could not close.");
      System.exit(-1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
