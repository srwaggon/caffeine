package caffeine;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.brain.instance.LeftBrain;
import caffeine.entity.brain.instance.RightBrain;
import caffeine.net.GameServer;
import caffeine.view.GUI;
import caffeine.view.screen.Screen;
import caffeine.world.Loc;
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
  private final List<Player> players = new LinkedList<Player>();
  private GUI gui = null;
  
  /* Main method */
  public static void main(String args[]) {
    
    // Get the game
    Caffeine caffeine = new Caffeine();
    
    // Add some data: A world, some entities
    Map map = new Map();
    caffeine.world().add(map);
    
    caffeine.createGUI();
    caffeine.gui().view(map);
    
    Player p1 = new Player(caffeine);
    caffeine.addPlayer(p1);
    
    Entity leftbot = new Entity(new Loc(0, 48, 80));
    leftbot.brain(new LeftBrain());
    leftbot.loc().tile().add(leftbot);
    
    Entity rightbot = new Entity(new Loc(0, 80, 48));
    rightbot.brain(new RightBrain());
    rightbot.loc().tile().add(rightbot);
    
    Screen s = caffeine.gui().getContentPane();
    s.camera().focusOn(p1.entity());
    
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
  
  public Set<Map> activeMaps() {
    Set<Map> activeMaps = new LinkedHashSet<Map>();
    for (Player p : players()) {
      activeMaps.add(p.entity().loc().map());
    }
    return activeMaps;
  }
  
  public String toString() {
    return "Caffeine Demo";
  }
  
  /* MUTATORS*/
  
  public void addPlayer(Player p) {
    players.add(p);
    p.entity().tile().add(p.entity());
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
    for (Map map : activeMaps()) {
      map.tick();
    }
  }
  
  public void createGUI() {
    if (gui == null) {
      gui = new GUI(this);
      new Thread(gui).start();
    }
  }
  
  public Entity entity(int entityID) {
    for (Map map : activeMaps()) {
      for (Entity e : map.entities()) {
        if (e.getID() == entityID) {
          return e;
        }
      }
    }
    return null;
  }
}
