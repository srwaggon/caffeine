package caffeine;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.PlayerEntity;
import caffeine.net.GameServer;
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
public final class Caffeine implements Game {
  /* Engine Fields */
  private final World world = new World(); // Space
  private final Clock clock = new Clock(); // Time
  private List<Player> players = new LinkedList<Player>();
  private GUI gui = null;
  
  /* Main method */
  public static void main(String args[]) {
    
    // Get the game
    Caffeine caffeine = new Caffeine();
    
    // Add some data: A world, some entities
    Map map = new Map();
    caffeine.world().add(map);
    
    caffeine.createGUI();
    Location l = new Location(0, 48, 48);
    PlayerEntity p1 = new PlayerEntity(l, caffeine.gui().getInteractions());
    map.add(p1);
    
    Screen s = caffeine.gui().getContentPane();
    s.camera().focusOn(p1);
    
    GameServer gs = new GameServer(caffeine, 4444);
    gs.run();
  }
  
  /* CONSTRUCTOR */
  Caffeine() {
    clock.add(new TimerTask() {
      public void run() {
        round();
      }
    });
  }
  
  /* ACCESSORS */
  public List<Entity> entities(int mapID) {
    return world.get(mapID).entities();
  }
  
  public GUI gui() {
    return gui;
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
  
  public void addPlayer(Player p) {
    players.add(p);
  }
  
  public int numRoundsPlayed() {
    return 0;
  }
  
  public void pause() {
    clock.stop();
  }
  
  public void play() {
    clock.start();
  }
  
  public List<Player> players() {
    return players;
  }
  
  public void round() {
    world.tick();
  }
}
