package caffeine.entity.brain;

import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.World;

public class LeftBrain extends Brain {
  protected Dir dir = Dir.DOWN;
  protected final double turnThresh = .99;
  protected boolean lastFailed = false;

  public LeftBrain(Mob self) {
    super(self);
  }

  public static Mob embody(World world) {
    return new LeftBrain(new Mob(world)).getSelf();
  }

  @Override
  public void tick() {
    if (turnThresh < Math.random() || lastFailed)
      dir = dir.prev();
    new Face(dir).performBy(self);
    lastFailed = !new Move(dir).performBy(self);
  }
}
