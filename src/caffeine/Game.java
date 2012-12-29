package caffeine;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import caffeine.entity.Entity;
import caffeine.gfx.GUI;
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
  protected final World world = new World(); // Space
  protected final ConcurrentHashMap<String, Entity> entities = new ConcurrentHashMap<String, Entity>();
  protected GUI gui;



  public Game() {
    Map map = new Map(Map.defaultMapData);
    //addEntity(new Mob(0), map);
    addMap(map);
  }


  public void addEntity(Entity e, int mapID){
    entities.put(e.ID, e);
    addEntity(e, world.getMap(mapID));
  }

  public void addEntity(Entity e, Map map){
    entities.put(e.ID, e);
    map.addEntity(e);
  }

  public void addMap(Map map) {
    world.addMap(map);
    List<Entity> ents = map.getEntities();
    for(int i = 0; i < ents.size(); i++) {
      Entity ent = ents.get(i);
      entities.put(ent.ID, ent);
    }
  }



  /* MUTATORS */


  public Collection<Entity> getEntities(int mapID) {
    return world.getMap(mapID).getEntities();
  }

  public Entity getEntity(String username) {
    return entities.get(username);
  }

  public Map getMap(int id) {
    return world.getMap(id);
  }




  public void run() {
    final double nsPerTick = 1000000000.0 / 60;
    long now, lastTime = System.nanoTime();
    double unprocessed = 0;

    while (true) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      while (unprocessed >= 1) {
        tick();
        unprocessed -= 1;
      }

      Map map = getMap(0);
      map.renderBackground(gui.screen);
      map.renderSprites(gui.screen);
      gui.screen.render();

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  /* UTILITY */
  public void start() {
    gui = new GUI("Caffeine Server");
    new Thread(this).start();
  }


  public void tick() {
    for (Map map : world.world.values()){
      map.tick();
    }
  }
}
