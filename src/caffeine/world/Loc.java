package caffeine.world;

//import caffeine.Server;
import caffeine.util.Util;

public class Loc {
  protected static World realm;
  protected int mapID;
  protected int x;
  protected int y;
  
  public static void setRealm(World realm) {
    Loc.realm = realm;
  }
  
  public Loc() {
    mapID = 0;
    x = 0;
    y = 0;
  }
  
  public Loc(int mapID, int x, int y) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }
  
  public Map map() {
    return Loc.realm.get(mapID);
  }
  
  public Tile tile() {
    return map().getTileAt(x, y);
  }
  
  public boolean isValid() {
    return map().onMap(x, y);
  }
  
  public void translate(int xdist, int ydist) {
    x += xdist;
    y += ydist;
  }
  
  public String toString() {
    return mapID + " " + x + " " + y;
  }
  
  public Loc copy() {
    return new Loc(mapID, x, y);
  }
  
  public double distanceTo(Loc loc) {
    return Util.pythagoras(loc.x - x, loc.y - y);
  }
  
  public int mapID() {
    return mapID;
  }
  
  public int x() {
    return x;
  }
  
  public int y() {
    return y;
  }
  
  public void set(Loc l) {
    mapID = l.mapID;
    x = l.x;
    y = l.y;
  }
  
  public void set(int mapID, int x, int y) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }
  
  public void translate(Direction dir, int magnitude) {
    int dx = 0, dy = 0;
    switch (dir) {
      case N:
        dy = -magnitude;
        break;
      case E:
        dx = magnitude;
        break;
      case S:
        dy = magnitude;
        break;
      case W:
        dx = -magnitude;
        break;
      default:
        break;
    }
    translate(dx, dy);
  }
}