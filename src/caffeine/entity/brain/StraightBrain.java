package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.World;

public class StraightBrain extends Brain {
  protected Dir forward;

  public StraightBrain(Entity self) {
    super(self);
    forward = self.getDirection();
  }

  public void setForward(Dir dir) {
    forward = dir;
  }

  public static Entity embody(World world) {
    return new StraightBrain(new Entity(world)).getEntity();
  }

  @Override
  public void tick() {
    if(!new Move(forward).performBy(self)){
      forward = forward.opposite();
    }
  }
}
