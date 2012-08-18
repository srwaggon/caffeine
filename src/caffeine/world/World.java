package caffeine.world;

import java.util.HashMap;

public class World {
  public HashMap<Integer, Map> world;
  protected Loc defaultSpawnLoc = new Loc(0, 48, 48);

  public World() {
    world = new HashMap<Integer, Map>();
  }

  public int getSize() {
    return world.size();
  }

  public void addMap(Map m) {
    world.put(m.getID(), m);
  }

  public Loc getDefaultSpawn() {
    return defaultSpawnLoc.copy();
  }

  public Map getMap(int mapID) {
    return world.get(mapID);
  }

  public Tile getTile(Loc loc) {
    return getMap(loc.mapID).getTileAt(loc.x, loc.y);
  }

  public boolean isValid(Loc l) {
    return world.containsKey(l.mapID) && world.get(l.mapID).isValidLoc(l.x, l.y);
  }

  /* *** MUTATORS *** */
  public void setDefaultSpawnLoc(Loc l) {
    if (isValid(l))
      defaultSpawnLoc = l;
  }

  public void tick() {
    for (Map m : world.values())
      m.tick();
  }

  @Override
  public String toString() {
    String s = "world " + world.size() + " {";
    for (int k : world.keySet())
      s += " " + k;
    return s + " }";
  }
}
