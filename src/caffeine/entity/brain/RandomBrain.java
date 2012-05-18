package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

/**
 * Creates a brain which moves in a random direction.
 * 
 * @author srwaggon
 */
public class RandomBrain extends Brain {
  Direction dir = Direction.pickOneAtRandom();
  int numSteps = 0;

  /**
   * Returns a list of actions planned for this brain's owners next turn.
   * 
   * @return a list of actions planned for this brain's owners next turn
   */
  @Override
  public List<Action> next(Entity actor, Map map) {
    actionPlan.clear();
    if (numSteps <= 0) {
      dir = Direction.pickOneAtRandom();
      numSteps = 9;
    }
    numSteps--;
    actionPlan.add(Move.fetch(dir));
    return actionPlan;
  }

}
