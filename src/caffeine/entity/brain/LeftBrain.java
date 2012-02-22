package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public class LeftBrain extends Brain {
  private Direction dir = Direction.S;
  
  public List<Action> next(Actor a) {
    actionPlan.clear();
    Move m = new Move(dir);
    
    if(a.motion().validMove(m, a)) {
      actionPlan.add(m);
    }else{
      dir = dir.prev();
    }
    return actionPlan;
  }
  
}
