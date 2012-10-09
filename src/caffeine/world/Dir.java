package caffeine.world;


public enum Dir {
  N(0,-1), E(1,0), S(0,1), W(-1,0);

  public final int dx;
  public final int dy;

  Dir(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }
  public Dir opposite(){
    return next().next();
  }

  /**
   * Returns the next direction from this enumeration from the following order: N, E, S, W
   * @return The next direction from this enumeration
   */
  public Dir next(){
    return Dir.values()[(ordinal() + 1) % Dir.size()];
  }

  /**
   * Returns the previous direction from this enumeration from the following order: W, S, E, N
   * @return The previous direction from this enumeration
   */
  public Dir prev(){
    return ordinal() > 0 ?
        Dir.values()[ordinal() - 1] :
          Dir.values()[Dir.size() - 1];
  }

  /**
   * Returns a random direction.
   * @return randomly selected direction
   */
  public static Dir pickOneAtRandom(){
    return Dir.values()[((int) Math.random()*Dir.size())];
  }

  /**
   * Returns the count of directions.  Should be 4.
   * @return the count of directions.
   */
  public static int size(){
    return Dir.values().length;
  }

  public static Dir whichDir(int xa, int ya){
    int absxa = Math.abs(xa);
    int absya = Math.abs(ya);

    if (ya < 0 && absxa < absya) return N;
    if (xa > 0 && absxa > absya) return E;
    if (ya > 0 && absxa < absya) return S;
    return W;
  }
}
