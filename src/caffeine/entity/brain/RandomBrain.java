package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
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

  public RandomBrain(Game g, Entity owner) {
    super(g, owner);
  }

  /**
   * Returns a list of actions planned for this brain's owners next turn.
   * 
   * @return a list of actions planned for this brain's owners next turn
   */
  @Override
  public List<Action> next() {
    actionPlan.clear();

    Caffeine caff = (Caffeine) game;

    if (numSteps <= 0) {
      dir = Direction.pickOneAtRandom();
      numSteps = 9;
    }
    numSteps--;
    Map map = caff.world().getMap(owner.getLoc().mapID());
    actionPlan.add(new Move(map, dir));
    return actionPlan;
  }
}
