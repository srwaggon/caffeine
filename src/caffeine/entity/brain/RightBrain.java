package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

public class RightBrain extends Brain {
  Direction dir = Direction.E;

  @Override
  public List<Action> next(Entity actor, Map m) {
    actionPlan.clear();
    Move move = new Move(dir);

    if (false) {
      actionPlan.add(move);
    } else {
      dir = dir.next();
    }
    return actionPlan;
  }

}
