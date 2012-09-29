package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.Map;

public class RightBrain extends Brain {
  Dir dir = Dir.RIGHT;

  public RightBrain(Mob self) {
    super(self);
  }

  public static Mob embody(Map map) {
    return new RightBrain(new Mob(map)).getSelf();
  }

  @Override
  public void tick() {
    self.setDir(dir);
    self.move(dir);
  }
}
