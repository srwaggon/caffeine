package caffeine.world;

import java.util.Random;

public enum Direction {

  NORTH, EAST, SOUTH, WEST,
    ;

  /**
     Returns number of constants in this type
     @return number of constants in this type
  */

  public static int size(){
    return values().length;
  }



  /**
     Returns character representation of the direction
     @return a single charater representation of direction
  */

  public String toString(){
    return this.name().substring(0,1);   
  }
  
  /**
  Returns a random direction
  @return randomly selected direction
*/
public static Direction getRandom() {
    Random generator = new Random();
    int i = generator.nextInt(size());
    return values()[i];
}
  


}