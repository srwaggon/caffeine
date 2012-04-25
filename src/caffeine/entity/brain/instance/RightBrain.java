package caffeine.entity.brain.instance;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.instance.Move;
import caffeine.entity.Entity;
import caffeine.entity.brain.Brain;
import caffeine.world.Direction;

public class RightBrain extends Brain {
  Direction dir = Direction.E;
  
  public List<Action> next(Entity actor) {
    actionPlan.clear();
    Move move = Move.fetch(dir);
    
    if (actor.motion().validMove(move, actor)) {
      actionPlan.add(move);
    } else {
      dir = dir.next();
    }
    return actionPlan;
  }
  
}
