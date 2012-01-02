package caffeine.action;

import caffeine.entity.Actor;
import caffeine.world.Direction;

/**
 * Represents a change-in-location action which can be performed on (by) Actor objects.
 * @author srwaggon
 *
 */
public class Move implements Action {
  protected Direction dir;

  /**
   * Creates a move object
   * @param motionVector a vector indicating the direction and magnitude of movement
   */
  public Move(Direction d){
    dir = d;
  }

  public Direction dir(){
    return dir;
  }

  public void perform(Actor performer){
    performer.motion().handleMove(this, performer);
  }

  public String toString(){
    return "move: " + dir;
  }

}
