package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.Map;

public class StraightBrain extends Brain {
  protected Dir forward;
  private int timetick = 0;
  public StraightBrain(Mob self) {
    super(self);
    forward = self.getDirection();
  }

  public void setForward(Dir dir) {
    forward = dir;
  }

  public static Mob embody(Map map) {
    return new StraightBrain(new Mob(map)).getSelf();
  }

  @Override
  public void tick() {

    if((timetick++ & 0x3) == 0)
      if(!new Move(forward).performBy(self))
        forward = forward.opposite();
  }
}
