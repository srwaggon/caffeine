package caffeine.entity.mob.brain;

import caffeine.entity.mob.Mob;
import caffeine.world.Dir;

public class RightBrain extends Brain {
  Dir dir = Dir.E;

  public RightBrain(Mob self) {
    super(self);
  }

  @Override
  public void tick() {
  }
}
