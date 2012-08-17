package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Dir;

public class Face implements Action {
  public final static Face NORTH = new Face(Dir.UP);
  public final static Face EAST = new Face(Dir.RIGHT);
  public final static Face SOUTH = new Face(Dir.DOWN);
  public final static Face WEST = new Face(Dir.LEFT);
  Dir dir;

  public Face(Dir d) {
    dir = d;
  }

  @Override
  public boolean performBy(Entity performer) {
    //if(!performer.isMoving()){
    performer.setDirection(dir);
    //}
    return true;
  }

}
