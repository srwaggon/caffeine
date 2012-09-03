package caffeine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.entity.Mob;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.StraightBrain;
import caffeine.gfx.GUI;
import caffeine.net.GameServer;
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
  private final List<Player> players = new LinkedList<Player>(); // Life
  private final GUI gui = new GUI("Caffeine Server"); // Light

  /* Main method */
  public static void main(String args[]) {

    Caffeine caffeine = new Caffeine();
    World world = caffeine.getWorld();

    Map map = new Map(Map.defaultMapData);
    world.addMap(map);

    Player p1 = new CaffeinePlayer(caffeine);
    caffeine.addPlayer(p1);

    new Mob(map);

    LeftBrain.embody(map);
    StraightBrain.embody(map);

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

  /* MUTATORS */

  public void addPlayer(Player player) {
    players.add(player);
    gui.addInputListener(player.getInputHandler());
  }

  public int numRoundsPlayed() {
    return 0;
  }

  public void start() {
    new Thread(this).start();
  }

  public void run() {
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

        world.getMap(0).renderBackground(gui.screen);
        world.getMap(0).renderSprites(gui.screen);
        gui.screen.render();
      }

      if (System.currentTimeMillis() - lastTimer1 > 1000) {
        lastTimer1 += 1000;
        System.out.println(ticks + " ticks, " + frames + " fps");
        frames = 0;
        ticks = 0;
      }
    }
  }


  public void tick() {

    for (Map map : world.world.values())
      map.tick();
  }
}
