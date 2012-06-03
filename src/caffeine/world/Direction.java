package caffeine.world;

import java.util.Random;

public enum Direction {
  NORTH, EAST, SOUTH, WEST;

  /**
   * Returns the next direction from this enumeration from the following order: N, E, S, W
   * @return The next direction from this enumeration
   */
  public Direction next(){
    return ordinal() < Direction.values().length - 1 ?
        Direction.values()[ordinal() + 1] :
          Direction.values()[0];
  }

  /**
   * Returns the previous direction from this enumeration from the following order: W, S, E, N
   * @return The previous direction from this enumeration
   */
  public Direction prev(){
    return ordinal() > 0 ?
        Direction.values()[ordinal() - 1] :
          Direction.values()[values().length - 1];
  }

  /**
   * Returns a random direction.
   * @return randomly selected direction
   */
  public static Direction pickOneAtRandom(){
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
