package caffeine.world;

//import caffeine.Server;
import caffeine.util.Util;

public class Loc {
  public int mapID;
  public int x;
  public int y;
  public int z;

  public Loc() {
    mapID = 0;
    x = 0;
    y = 0;
    z = 0;
  }

  public Loc(int mapID, int x, int y, int z) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }

  public Loc project(int xdist, int ydist) {
    return new Loc(mapID, x + xdist, y + ydist, 0);
  }

  public Loc translate(double xdist, double ydist) {
    x += xdist;
    y += ydist;
    return this;
  }

  @Override
  public String toString() {
    return "M " + mapID + " X " + x + " Y " + y + " Z " + z;
  }

  public Loc copy() {
    return new Loc(mapID, x, y, z);
  }

  public double distanceTo(Loc loc) {
    return Util.pythagoras(loc.x - x, loc.y - y);
  }

  public void set(Loc l) {
    mapID = l.mapID;
    x = l.x;
    y = l.y;
    z = l.z;
  }

  public void set(int mapID, int x, int y, int z) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
