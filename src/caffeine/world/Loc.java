package caffeine.world;

//import caffeine.Server;
import caffeine.util.Util;

public class Loc {
  protected int mapID;
  protected int x;
  protected int y;

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

  public Loc project(int xdist, int ydist) {
    return new Loc(mapID, x + xdist, y + ydist);
  }

  public void translate(int xdist, int ydist) {
    x += xdist;
    y += ydist;
  }

  @Override
  public String toString() {
    return String.format("{%d:(%d,%d)}", mapID, x, y);
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

  public Loc translate(Direction dir, int magnitude) {
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
    return this;
  }

  public Loc project(Direction dir, int magnitude) {
    return copy().translate(dir, magnitude);
  }
}
