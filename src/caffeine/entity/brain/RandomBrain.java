package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.util.Util;
import caffeine.world.Direction;
import caffeine.world.Map;

/**
 * Creates a brain which moves in a random direction.
 * 
 * @author srwaggon
 */
public class RandomBrain extends Brain {
  Direction dir = Direction.pickOneAtRandom();
  private final double turnThresh = .90;
  private int numSteps = 0;

  public RandomBrain(Entity self) {
    super(self);
  }

  /**
   * Returns a list of actions planned for this brain's owners next turn.
   * 
   * @return a list of actions planned for this brain's owners next turn
   */
  @Override
  public List<Action> getActionPlan() {
    actionPlan.clear();

    Map map = self.getWorld().getMap(self.getLoc().mapID);

    //if (turnThresh < Math.random()) {
    if (numSteps == 0) {
      if (Util.coinflip()) {
        dir = dir.next();
      } else {
        dir = dir.prev();
      }
      numSteps = (int) (Math.random() * 20) + 5;
    }
    numSteps--;
    actionPlan.add(new Face(dir));
    actionPlan.add(new Move(dir));
    return actionPlan;
  }
}
