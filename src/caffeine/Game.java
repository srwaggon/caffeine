package caffeine;

import java.util.Collection;
import java.util.HashMap;

import caffeine.entity.Entity;
import caffeine.entity.Mob;
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
public class Game implements Runnable {


  /* FIELDS */
  protected int port = 4444;
  protected static final World world = new World(); // Space
  protected static final HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected static final GUI gui = new GUI("Caffeine Server"); // Light
  protected Map currentMap;



  /* MAIN METHOD */


  public static void main(String args[]) {
    Game game = new Game();
    game.addMap(new Map(Map.defaultMapData));
    game.addEntity(new Mob(), game.getCurrentMap());
    game.start();
  }





  /* ACCESSORS */


  public Map getCurrentMap() {
    return world.getMap(0);
  }

  public Entity getEntity(int id) {
    return entities.get(id);
  }

  public Collection<Entity> getEntities(int mapID) {
    return world.getMap(mapID).entities();
  }



  /* MUTATORS */


  public void addEntity(Entity e, int mapID){
    addEntity(e, world.getMap(mapID));
  }

  public void addEntity(Entity e, Map map){
    entities.put(e.getID(), e);
    map.addEntity(e);
  }

  public void addMap(Map map) {
    world.addMap(map);
  }




  /* UTILITY */


  public void start() {
    new Thread(this).start();
    GameServer gs = new GameServer(this, port);
    gs.run();
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

        Map map = getCurrentMap();
        map.renderBackground(gui.screen);
        map.renderSprites(gui.screen);
        gui.screen.render();
      }

      if (System.currentTimeMillis() - lastTimer1 > 1000) {
        lastTimer1 += 1000;
        //System.out.println(ticks + " ticks, " + frames + " fps");
        frames = 0;
        ticks = 0;
      }
    }
  }


  private void tick() {
    for (Map map : world.world.values()){
      map.tick();
    }
  }
}
