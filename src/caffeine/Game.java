package caffeine;

import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.net.Server;
import caffeine.view.GUI;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the
 * mechanics, but are broken down into smaller components.
 * 
 * @author Fnar
 */
public final class Game {
  /* Engine Fields */
  private final static Game INSTANCE = new Game(); // Instance
  private final World world = new World(); // Space
  private final Clock clock = new Clock(); // Time
  private GUI gui = null;
  
  /* Networking Fields */
  private final Server server = new Server(4444);
  
  /* CONSTRUCTORS */
  private Game() {
    clock.add(new TimerTask() {
      public void run() {
        world.tick();
      }
    });
    new Thread(server).start();
  }
  
  /* ACCESSORS */
  public List<Entity> entities(int mapID) {
    return world.get(mapID).entities();
  }
  
  public GUI gui() {
    return gui;
  }
  
  public static Game instance() {
    return Game.INSTANCE;
  }
  
  public World world() {
    return world;
  }
  
  /* HELPERS */
  public void createGUI() {
    if (gui == null) {
      gui = new GUI(world.get(0));
      gui.setTitle("Caffeine Server");
      new Thread(gui).start();
    }
  }
}
