package caffeine.world;

public enum Dir {
  N(0, -1),
  E(1, 0),
  S(0, 1),
  W(-1, 0);
  
  public final int dx;
  public final int dy;
  
  Dir(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }
  
  public Dir opposite() {
    return Dir.values()[(Dir.values().length / 2 + this.ordinal())
        % Dir.values().length];
  }
  
  public Dir next() {
    return Dir.values()[(ordinal() + 1) % Dir.count()];
  }
  
  public Dir prev() {
    return ordinal() > 0 ? Dir.values()[ordinal() - 1] : Dir.values()[Dir
        .count() - 1];
  }

  public static Dir pickOneAtRandom() {
    return Dir.values()[((int) Math.random() * Dir.count())];
  }

  public static int count() {
    return Dir.values().length;
  }
  
  public static Dir getXDir(int dx) {
    return dx > 0 ? E : dx < 0 ? W : null;
  }

  public static Dir getYDir(int dy) {
    return dy < 0 ? N : dy > 0 ? S : null;
  }

  public static Dir getDir(int dx, int dy) {
    for (Dir dir : values()) {
      if ((dx == 0 || dx / Math.abs(dx) == dir.dx)
          && (dy == 0 || dy / Math.abs(dy) == dir.dy)) {
        return dir;
      }
    }
    return null;
  }
}
