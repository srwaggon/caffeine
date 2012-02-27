package caffeine.world;

import java.util.HashMap;

public class World {
  public HashMap<Integer, Map> world   = new HashMap<Integer, Map>();
  static int                   numMaps = 0;
  
  public World() {
    Location.setRealm(this);
  }
  
  public int size() {
    return world.size();
  }
  
  public int add(Map m) {
    world.put(World.numMaps, m);
    return World.numMaps++;
  }
  
  public Map get(int mapID) {
    return world.get(mapID);
  }
  
  public void tick() {
    for (Map m : world.values()) {
      m.tick();
    }
  }
  
  public String toString() {
    String s = "";
    for (Map m : world.values()) {
      s += m;
    }
    return s;
  }
}
