package caffeine.world;

import java.util.HashMap;

public class World {
  public HashMap<Integer, Map> world = new HashMap<Integer, Map>();
  
  public World() {
    Loc.setRealm(this);
  }
  
  public int size() {
    return world.size();
  }
  
  public void add(Map m) {
    world.put(m.getID(), m);
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
    String s = "world " + world.size() + " ";
    for (int k : world.keySet()) {
      s += k;
    }
    return s;
  }
}
