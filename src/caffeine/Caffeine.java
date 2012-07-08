package caffeine;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import caffeine.entity.Entity;
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
public class Caffeine implements Runnable {
  /* Engine Fields */
  private final World world = new World(); // Space
  //private final Clock clock = new Clock(); // Time
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
    /*
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
     */

    Screen s = caffeine.gui().getScreen();
    s.camera().focusOn(p1.getEntity());

    caffeine.start();

    GameServer gs = new GameServer(caffeine, 4444);
    gs.run();

  }

  /* CONSTRUCTOR */
  Caffeine() {

  }

  /* ACCESSORS */
  public Collection<Entity> entities(int mapID) {
    return world.getMap(mapID).entities();
  }

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

  public void addPlayer(Player player) {
    players.add(player);
    gui.addInputListener(player.getInputListener());
  }

  public int numRoundsPlayed() {
    return 0;
  }

  public void start(){
    new Thread(this).start();
  }

  public void run(){
    long lastTime = System.nanoTime();
    double unprocessed = 0;
    double nsPerTick = 1000000000.0 / 60;
    int frames = 0;
    int ticks = 0;
    long lastTimer1 = System.currentTimeMillis();

    while (true) {
      long now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;
      boolean shouldRender = true;
      while (unprocessed >= 1) {
        ticks++;
        tick();
        unprocessed -= 1;
        shouldRender = true;
      }

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (shouldRender) {
        frames++;
        gui.repaint();
      }

      if (System.currentTimeMillis() - lastTimer1 > 1000) {
        lastTimer1 += 1000;
        System.out.println(ticks + " ticks, " + frames + " fps");
        frames = 0;
        ticks = 0;
      }
    }
  }


  public List<Player> players() {
    return players;
  }


  public void tick() {
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
