package caffeine.entity.brain;

import java.util.ArrayList;
import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.util.Util;
import caffeine.world.Direction;


/**
 * Creates a brain which moves in a random direction.
 * @author Fnar
 */
public class RandomBrain implements Brain {
  Direction dir = Direction.pickOneAtRandom();

  /**
   * Returns a list of actions planned for this brain's owners next turn.
   * @return a list of actions planned for this brain's owners next turn
   */
  public List<Action> next(Actor actor) {
    ArrayList<Action> actionList = new ArrayList<Action>();
    if(Util.coinflip()) {
      dir = Direction.pickOneAtRandom();
      actionList.add(new Move(dir));
    }
    return actionList;
  }

}
