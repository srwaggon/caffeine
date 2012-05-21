package caffeine.entity.brain;

import java.util.List;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;

public class RightBrain extends Brain {
  Direction dir = Direction.E;

  public RightBrain(Game g, Entity owner){
    super(g, owner);
  }

  @Override
  public List<Action> next() {
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
