package caffeine.entity.brain;

import java.util.ArrayList;
import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public class LeftBrain implements Brain {
  private Direction dir = Direction.S;

  public List<Action> next(Actor a) {
    List<Action> actions = new ArrayList<Action>();
    Move m = new Move(dir);

    if(a.motion().validMove(m, a)) {
      actions.add(m);
    }else{
      dir = dir.prev();
    }
    return actions;
  }

}
