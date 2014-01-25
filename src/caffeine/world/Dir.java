package caffeine.world;

public enum Dir {
  N(0, -1),
  NE(1, -1),
  E(1, 0),
  SE(1, 1),
  S(0, 1),
  SW(-1, 1),
  W(-1, 0),
  NW(-1, -1);
  
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
  
  /**
   * Returns the next direction from this enumeration from the following order:
   * N, E, S, W
   * 
   * @return The next direction from this enumeration
   */
  public Dir next() {
    return Dir.values()[(ordinal() + 1) % Dir.count()];
  }
  
  /**
   * Returns the previous direction from this enumeration from the following
   * order: W, S, E, N
   * 
   * @return The previous direction from this enumeration
   */
  public Dir prev() {
    return ordinal() > 0 ? Dir.values()[ordinal() - 1] : Dir.values()[Dir
        .count() - 1];
  }
  
  /**
   * Returns a random direction.
   * 
   * @return randomly selected direction
   */
  public static Dir pickOneAtRandom() {
    return Dir.values()[((int) Math.random() * Dir.count())];
  }
  
  /**
   * Returns the count of directions. Should be 4.
   * 
   * @return the count of directions.
   */
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
