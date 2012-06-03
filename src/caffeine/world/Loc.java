package caffeine.world;

//import caffeine.Server;
import caffeine.util.Util;

public class Loc {
  public int mapID;
  public double x;
  public double y;

  public Loc() {
    mapID = 0;
    x = 0;
    y = 0;
  }

  public Loc(int mapID, double x, double y) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }

  public Loc project(double xdist, double ydist) {
    return new Loc(mapID, x + xdist, y + ydist);
  }

  public Loc translate(double xdist, double ydist) {
    x += xdist;
    y += ydist;
    return this;
  }

  @Override
  public String toString() {
    return String.format("{%d:(%f,%f)}", mapID, x, y);
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

  public void set(int mapID, double x, double y) {
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }
}
