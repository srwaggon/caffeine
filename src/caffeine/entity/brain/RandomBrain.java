package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.util.Util;
import caffeine.world.Dir;

/**
 * Creates a brain which moves in a random direction.
 * 
 * @author srwaggon
 */
public class RandomBrain extends Brain {
  Dir dir = Dir.pickOneAtRandom();
  private int numSteps = 0;

  public RandomBrain(Mob self) {
    super(self);
  }

  public void tick() {
    if (numSteps == 0) {
      if (Util.coinflip())
        dir = dir.next();
      else dir = dir.prev();
      numSteps = (int) (Math.random() * 20) + 5;
    }
    numSteps--;
    self.setDir(dir);
    self.move(dir);

  }
}
