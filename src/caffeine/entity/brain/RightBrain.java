package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public class RightBrain implements Brain {
  Direction dir = Direction.E;

  public ArrayList<Action> next(Actor a) {
    ArrayList<Action> actions = new ArrayList<Action>();
    Move m = new Move(dir);

    if(a.motion().validMove(m, a)) {
      actions.add(m);
    }else{
      dir = dir.next();
    }

    return actions;
  }

}
