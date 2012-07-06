package caffeine.world;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Boulder;

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

  public Map addMap(String s) {
    Scanner scans = new Scanner(s);
    int numCols = Integer.parseInt(scans.next());
    int numRows = Integer.parseInt(scans.next());
    Map map = new Map(numCols, numRows);
    addMap(map);

    String line = scans.next();
    for (int i = 0; i < numRows * numCols; i++) {
      int x = i % numCols;
      int y = i / numCols;
      char c = line.charAt(i);
      Tile t = map.getTile(x, y);
      if (c == '@') {
        Loc spawnLoc = new Loc(map.id, x * Map.tileSize + Map.tileSize / 2, y
            * Map.tileSize + Map.tileSize / 2);
        new Boulder(this, spawnLoc);
      } else if (c == '#') {
        t.setPass(false);
        t.setSprite(10);
      }
    }

    return map;
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
    return world.containsKey(l.mapID) && world.get(l.mapID).inRange(l.x, l.y);
  }

  /* *** MUTATORS *** */
  public void setDefaultSpawnLoc(Loc l) {
    if (isValid(l)) {
      defaultSpawnLoc = l;
    }
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
