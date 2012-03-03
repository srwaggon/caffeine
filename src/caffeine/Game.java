package caffeine;

import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.net.Server;
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
public final class Game {
  /* Engine Fields */
  private final static Game INSTANCE = new Game(); // Instance
  private final World world = new World(); // Space
  private final Clock clock = new Clock(); // Time
  private GUI gui = null;
  
  /* Networking Fields */
  private final Server server = new Server(4444);
  
  /* Main method */
  public static void main(String args[]) {
    
    // Get the game
    Game game = Game.instance();
    
    // Add some data: A world, some entities
    Map map = new Map();
    game.world().add(map);
    
    game.createGUI();
    Location l = new Location(0, 48, 48);
    Player p1 = new Player(l, game.gui().getInteractions());
    map.add(p1);
    
    Screen s = game.gui().getContentPane();
    s.camera().focusOn(p1);
    
  }
  
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
