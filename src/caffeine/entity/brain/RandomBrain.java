package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
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
  private double turnThresh = .90;
  private int numSteps = 0;

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
    Map map = caff.world().getMap(self.getLoc().mapID);

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
    actionPlan.add(new Move(map, dir));
    return actionPlan;
  }
}
