package caffeine.world;

import java.util.HashMap;

public class World {
  public HashMap<Integer, Map> world;

  public World() {
    world = new HashMap<Integer, Map>();
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

  public Tile getTile(Loc loc) {
    return get(loc.mapID()).getTileAt(loc.x(), loc.y());
  }

  public void tick() {
    for (Map m : world.values()) {
      m.tick();
    }
  }

  @Override
  public String toString() {
    String s = "world " + world.size() + " {";
    for (int k : world.keySet()) {
      s += " " + k;
    }
    return s + " }";
  }
}
