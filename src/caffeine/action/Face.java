package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class Face implements Action {
  public final static Face NORTH = new Face(Direction.NORTH);
  public final static Face EAST = new Face(Direction.EAST);
  public final static Face SOUTH = new Face(Direction.SOUTH);
  public final static Face WEST = new Face(Direction.WEST);
  Direction dir;

  private Face(Direction d) {
    dir = d;
  }

  @Override
  public boolean performBy(Entity performer) {
    performer.setFacing(dir);
    return true;
  }

}
