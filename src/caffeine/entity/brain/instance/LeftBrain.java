package caffeine.entity.brain.instance;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.instance.Move;
import caffeine.entity.Entity;
import caffeine.entity.brain.Brain;
import caffeine.world.Direction;

public class LeftBrain extends Brain {
  private Direction dir = Direction.S;
  
  public List<Action> next(Entity actor) {
    actionPlan.clear();
    Move move = Move.fetch(dir);
    
    if (actor.motion().validMove(move, actor)) {
      actionPlan.add(move);
    } else {
      dir = dir.prev();
    }
    return actionPlan;
  }
  
}
