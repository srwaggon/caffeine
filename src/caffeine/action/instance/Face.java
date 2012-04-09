package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public class Face implements Action {
  public final static Face NORTH = new Face(Direction.N);
  public final static Face EAST = new Face(Direction.E);
  public final static Face SOUTH = new Face(Direction.S);
  public final static Face WEST = new Face(Direction.W);
  Direction dir;
  
  private Face(Direction d) {
    dir = d;
  }
  
  public void performBy(Actor performer) {
    performer.motion().face(dir);
  }
  
}