package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class Move implements Action {
  protected Direction dir;

  public Move(Direction dir) {
    this.dir = dir;
  }


  @Override
  public boolean performBy(Entity actor) {
    actor.setDirection(dir);
    actor.isMoving(true);
    return true;
  }
}
