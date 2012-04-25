package caffeine.entity.brain.instance;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.instance.Move;
import caffeine.entity.Entity;
import caffeine.entity.brain.Brain;
import caffeine.world.Direction;

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
  public List<Action> next(Entity actor) {
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
