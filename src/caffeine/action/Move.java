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
    Motion motion = actor.getMotion();
    motion.setDirection(dir);
    motion.isMoving(true);
    return true;
  }
}
