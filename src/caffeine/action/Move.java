package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class Move implements Action {
  Direction dir;

  public Move(Direction dir) {
    this.dir = dir;
  }

  @Override
  public boolean performBy(Entity actor) {
    double dx = actor.getSpeed() * dir.dx();
    double dy = actor.getSpeed() * dir.dy();
    actor.setDirection(dir);
    return actor.move(dx, dy, true);
  }
}
