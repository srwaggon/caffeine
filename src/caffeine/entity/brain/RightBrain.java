package caffeine.entity.brain;

import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.World;

public class RightBrain extends Brain {
  Dir dir = Dir.RIGHT;

  public RightBrain(Entity self) {
    super(self);
  }

  public static Entity embody(World world) {
    return new RightBrain(new Entity(world)).getEntity();
  }

  @Override
  public void tick() {
    new Face(dir).performBy(self);
    new Move(dir).performBy(self);
  }
}
