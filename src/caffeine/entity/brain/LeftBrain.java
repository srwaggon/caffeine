package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

public class LeftBrain extends Brain {
  private Direction dir = Direction.S;

  @Override
  public List<Action> next(Entity actor, Map map) {
    actionPlan.clear();
    Move move = Move.fetch(dir);

    if (false) {
      actionPlan.add(move);
    } else {
      dir = dir.prev();
    }
    return actionPlan;
  }

}
