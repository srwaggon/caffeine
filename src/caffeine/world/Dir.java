package caffeine.world;

import java.util.Random;

public enum Dir {
  UP, RIGHT, DOWN, LEFT;

  public int dx(){
    int dx = 0;
    dx = this == RIGHT ?  1 : dx;
    dx = this == LEFT ? -1 : dx;
    return dx;
  }

  public int dy(){
    int dy = 0;
    dy = this == UP ? -1 : dy;
    dy = this == DOWN ?  1 : dy;
    return dy;
  }

  public Dir opposite(){
    return next().next();
  }

  /**
   * Returns the next direction from this enumeration from the following order: N, E, S, W
   * @return The next direction from this enumeration
   */
  public Dir next(){
    return ordinal() < Dir.values().length - 1 ?
        Dir.values()[ordinal() + 1] :
          Dir.values()[0];
  }

  /**
   * Returns the previous direction from this enumeration from the following order: W, S, E, N
   * @return The previous direction from this enumeration
   */
  public Dir prev(){
    return ordinal() > 0 ?
        Dir.values()[ordinal() - 1] :
          Dir.values()[values().length - 1];
  }

  /**
   * Returns a random direction.
   * @return randomly selected direction
   */
  public static Dir pickOneAtRandom(){
    Random rndm = new Random();
    int i = rndm.nextInt(size());
    return values()[i];
  }

  /**
   * Returns the count of directions.  Should be 4.
   * @return the count of directions.
   */
  public static int size(){
    return values().length;
  }

  /**
   * Returns character representation of the direction.
   * @return a single character representation indicating direction
   */
  public String toString(){
    return name().substring(0,1);
  }
}
