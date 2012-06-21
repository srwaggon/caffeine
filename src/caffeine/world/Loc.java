package caffeine.world;

//import caffeine.Server;
import caffeine.util.Util;

public class Loc {
  public int mapID;
  public int x;
  public int y;

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

  public Coord coord() {
    return new Coord(x, y);
  }

  public Loc project(int xdist, int ydist) {
    return new Loc(mapID, x + xdist, y + ydist);
  }

  public Loc translate(double xdist, double ydist) {
    x += xdist;
    y += ydist;
    return this;
  }

  @Override
  public String toString() {
    return "{" + mapID + ":(" + x + "," + y + ")}";
  }

  public Loc copy() {
    return new Loc(mapID, x, y);
  }

  public double distanceTo(Loc loc) {
    return Util.pythagoras(loc.x - x, loc.y - y);
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
}
