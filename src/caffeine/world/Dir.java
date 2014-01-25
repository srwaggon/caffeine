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
  
  public static Dir whichDir(int dx, int dy) {
    for (Dir dir : values()) {
      if (dx / Math.abs(dx) == dir.dx && dy / Math.abs(dy) == dir.dy) {
        return dir;
      }
    }
    return null;
  }
}
