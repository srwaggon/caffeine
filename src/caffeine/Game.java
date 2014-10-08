package caffeine;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.entity.mob.Mob;
import caffeine.world.Map;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heart beat, as well as the
 * mechanisms, but are broken down into smaller components.
 * 
 * @author Samuel Waggoner
 * @email samuel.waggoner@gmail.com
 */
public class Game extends Thread {
  
  private final HashMap<String, Entity> entities = new HashMap<String, Entity>();
  private final HashMap<String, Player> players = new HashMap<String, Player>();
  private final HashMap<Integer, Map> world = new HashMap<Integer, Map>();
  private static final int TICKS_PER_SECOND = 60;
  public static final double NANOSECONDS_PER_SECOND = 1000000000.0;
  
  public Game() {
    Map map = new Map(Map.defaultMapData);
    putMap(map);
  }
  
  public void addPlayer(Player player) {
    players.put(player.getUsername(), player);
  }
  
  public boolean hasPlayer(String username) {
    return players.containsKey(username);
  }
  
  public Player removePlayer(String username) {
    return players.remove(username);
  }
  
  public void addEntity(Entity e) {
    addEntity(e, 0);
  }
  
  public void addEntity(Entity e, int mapID) {
    addEntity(e, world.get(mapID));
  }
  
  public void addEntity(Entity e, Map map) {
    entities.put(e.ID, e);
    map.addEntity(e);
  }
  
  public void putMap(Map map) {
    world.put(map.getID(), map);
    List<Entity> ents = map.getEntities();
    for (int i = 0; i < ents.size(); i++) {
      Entity ent = ents.get(i);
      entities.put(ent.ID, ent);
    }
  }
  
  public Collection<Entity> getEntities(int mapID) {
    return world.get(mapID).getEntities();
  }
  
  public Entity getEntity(String username) {
    return entities.get(username);
  }
  
  public Mob getMob(String username) {
    return (Mob) entities.get(username);
  }

  public int getMapCount() {
    return world.size();
  }
  
  public Map getMap(int mapID) {
    return world.get(mapID);
  }
  
  @Override
  public void run() {
    long now, lastTime = System.nanoTime();
    double unprocessed = 0;
    int ticks = 0;
    long secondTimer = System.currentTimeMillis();
    while (true) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / getTicksPerSecond();
      lastTime = now;
      
      // System.out.println(unprocessed);
      
      while (unprocessed >= 1) {
        ticks++;
        tick();
        unprocessed -= 1;
      }
      
      long thisTime = System.currentTimeMillis();
      if (thisTime - secondTimer > 1000) {
        secondTimer = thisTime;
        System.out.println(ticks + " ticks");
        ticks = 0;
      }

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

  protected double getTicksPerSecond() {
    return NANOSECONDS_PER_SECOND / TICKS_PER_SECOND;
  }
  
  @Override
  public void start() {
    new Thread(this).start();
  }
  
  public void tick() {
    for (Map m : world.values()) {
      m.tick();
    }
  }
}
