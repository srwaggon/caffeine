package caffeine.world;

import java.util.HashMap;

public class World {
  public HashMap<Integer, Map> world;

  public World() {
    world = new HashMap<Integer, Map>();
  }

  public int getSize() {
    return world.size();
  }

  public void addMap(Map m) {
    world.put(m.getID(), m);
  }

  public Map getMap(int mapID) {
    return world.get(mapID);
  }

  public Tile getTile(Loc loc) {
    return getMap(loc.mapID).getTileAt((int)loc.x, (int)loc.y);
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
