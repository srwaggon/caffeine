package caffeine.world;

public class Coord {
  protected int x, y;
  
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int x() {
    return x;
  }
  
  public int y() {
    return y;
  }
  
  public Coord next(Direction dir) {
    Coord next = null;
    switch (dir) {
      case N:
        next = new Coord(x, y - 1);
        break;
      case E:
        next = new Coord(x + 1, y);
        break;
      case S:
        next = new Coord(x, y + 1);
        break;
      case W:
        next = new Coord(x - 1, y);
        break;
      default:
        break;
    }
    return next;
  }
}
