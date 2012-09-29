package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.Dir;

public class RightBrain extends Brain {
  Dir dir = Dir.RIGHT;

  public RightBrain(Mob self) {
    super(self);
  }

  @Override
  public void tick() {
    self.setDir(dir);
    self.move(dir);
  }
}
