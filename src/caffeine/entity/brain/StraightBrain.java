package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

public class StraightBrain extends Brain {
  protected Direction forward;

  public StraightBrain(Entity self) {
    super(self);
    forward = self.getDirection();
  }

  public void setForward(Direction dir) {
    forward = dir;
  }

  @Override
  public List<Action> next() {
    actionPlan.clear();

    Map currentMap = self.getWorld().getMap(self.getLoc().mapID);
    Move next = new Move(forward);

    // TODO: Requiring dry-runs allows entities to attempt to perform
    // invalid moves.

    forward = forward.opposite();

    actionPlan.add(next);
    return actionPlan;
  }
}
