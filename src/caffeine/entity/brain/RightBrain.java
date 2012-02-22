package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public class RightBrain extends Brain {
  Direction dir = Direction.E;
  
  public List<Action> next(Actor a) {
    actionPlan.clear();
    Move m = new Move(dir);
    
    if(a.motion().validMove(m, a)) {
      actionPlan.add(m);
    }else{
      dir = dir.next();
    }
    return actionPlan;
  }
  
}
