package caffeine;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.RightBrain;
import caffeine.net.GameServer;
import caffeine.view.GUI;
import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;
import caffeine.world.Loc;
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

    // Add some data: A world, some entities
    Map map = new Map();
    caffeine.world().addMap(map);

    WorldScreen ws = new WorldScreen();
    ws.setMap(map);
    caffeine.gui().setScreen(ws);

    Player p1 = new Player(caffeine);
    caffeine.addPlayer(p1);

    Entity leftbot = new Entity(new Loc(0, 48, 80));
    leftbot.setBrain(new LeftBrain(caffeine, leftbot));
    map.getTileAt(48, 80).addEntity(leftbot);

    Entity rightbot = new Entity(new Loc(0, 80, 48));
    rightbot.setBrain(new RightBrain(caffeine, rightbot));
    map.getTileAt(80, 48).addEntity(rightbot);

    Screen s = caffeine.gui().getScreen();
    s.camera().focusOn(p1.entity());

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
    new Thread(gui).start();
    play();
  }

  /* ACCESSORS */
  public List<Entity> entities(int mapID) {
    return world.getMap(mapID).entities();
  }

  @Override
  public GUI gui() {
    return gui;
  }

  public World world() {
    return world;
  }

  public Set<Map> activeMaps() {
    Set<Map> activeMaps = new LinkedHashSet<Map>();
    for (Player p : players()) {
      activeMaps.add(world.getMap(p.entity().getLoc().mapID()));
    }
    return activeMaps;
  }

  /* MUTATORS */
  @Override
  public void addPlayer(Player p) {
    players.add(p);
    world.getTile(p.entity().getLoc()).addEntity(p.entity());
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
    clock.start();
  }

  @Override
  public List<Player> players() {
    return players;
  }

  @Override
  public void round() {
    for (Map map : activeMaps()) {
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
