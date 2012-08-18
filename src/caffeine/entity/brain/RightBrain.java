package caffeine.entity.brain;

import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.World;

public class RightBrain extends Brain {
  Dir dir = Dir.RIGHT;

  public RightBrain(Mob self) {
    super(self);
  }

  public static Mob embody(World world) {
    return new RightBrain(new Mob(world)).getSelf();
  }

  @Override
  public void tick() {
    new Face(dir).performBy(self);
    new Move(dir).performBy(self);
  }
}
