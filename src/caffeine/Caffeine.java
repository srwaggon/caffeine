package caffeine;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.RandomBrain;
import caffeine.entity.brain.RightBrain;
import caffeine.entity.brain.StraightBrain;
import caffeine.net.GameServer;
import caffeine.view.GUI;
import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;
import caffeine.world.Map;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heart beat, as well as the
 * mechanisms, but are broken down into smaller components.
 * 
 * @author srwaggon
 */
public class Caffeine implements Game {
  /* Engine Fields */
  private final World world = new World(); // Space
  private final Clock clock = new Clock(); // Time
  private final List<Player> players = new LinkedList<Player>(); // Life
  private final GUI gui = new GUI("Caffeine Server"); // Light

  /* Main method */
  public static void main(String args[]) {

    Caffeine caffeine = new Caffeine();
    World world = caffeine.getWorld();

    // Add some data: A world, some entities
    Map map = world.addMap(Map.defaultMapString);

    // create a GUI
    WorldScreen ws = new WorldScreen();
    ws.setMap(map);
    caffeine.gui().setScreen(ws);

    // add a player
    CaffeinePlayer p1 = new CaffeinePlayer(caffeine);
    caffeine.addPlayer(p1);

    // add some AI
    Entity leftbot = new Entity(world);
    new LeftBrain(leftbot);

    Entity rightbot = new Entity(world);
    new RightBrain(rightbot);

    Entity straightbot = new Entity(world);

    new StraightBrain(straightbot);

    Entity randombot = new Entity(world);
    new RandomBrain(randombot);

    randombot = new Entity(world);
    new RandomBrain(randombot);

    randombot = new Entity(world);
    new RandomBrain(randombot);

    Screen s = caffeine.gui().getScreen();
    s.camera().focusOn(p1.getEntity());

    caffeine.play();

    GameServer gs = new GameServer(caffeine, 4444);
    gs.run();

  }

  /* CONSTRUCTOR */
  Caffeine() {
    clock.add(new TimerTask() {
      @Override
      public void run() {
        round();
      }
    });
  }

  /* ACCESSORS */
  public Collection<Entity> entities(int mapID) {
    return world.getMap(mapID).entities();
  }

  @Override
  public GUI gui() {
    return gui;
  }

  public World getWorld() {
    return world;
  }

  public Set<Map> activeMaps() {
    Set<Map> activeMaps = new LinkedHashSet<Map>();
    for (Player p : players()) {
      activeMaps.add(world.getMap(p.getEntity().getLoc().mapID));
    }
    return activeMaps;
  }

  /* MUTATORS */
  @Override
  public void addPlayer(Player player) {
    players.add(player);
    gui.addInputListener(player.getInputListener());
  }

  @Override
  public int numRoundsPlayed() {
    return 0;
  }

  @Override
  public void pause() {
    clock.stop();
  }

  @Override
  public void play() {
    new Thread(gui).start();
    clock.start();
  }

  @Override
  public List<Player> players() {
    return players;
  }

  @Override
  public void round() {
    //for (Map map : activeMaps()) {
    for (Map map : world.world.values()) {
      map.tick();
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
