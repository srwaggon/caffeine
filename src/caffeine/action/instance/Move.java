package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.world.Direction;

/**
 * Represents a change-in-location action which can be performed on (by) Actor
 * objects.
 * 
 * @author srwaggon
 * 
 */
public class Move implements Action {
  protected Direction dir;
  public static Move NORTH = new Move(Direction.N);
  public static Move EAST = new Move(Direction.E);
  public static Move SOUTH = new Move(Direction.S);
  public static Move WEST = new Move(Direction.W);
  
  /**
   * Creates a move object
   * 
   * @param motionVector
   *          a vector indicating the direction and magnitude of movement
   */
  private Move(Direction d) {
    dir = d;
  }
  
  public Direction dir() {
    return dir;
  }
  
  public static Move fetch(Direction dir) {
    switch (dir) {
      case N:
        return Move.NORTH;
      case E:
        return Move.EAST;
      case S:
        return Move.SOUTH;
      default:
        return Move.WEST;
    }
  }
  
  public void perform(Actor performer) {
    performer.motion().handleMove(this, performer);
  }
  
  public String toString() {
    return "move: " + dir;
  }
  
}
