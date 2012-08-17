package caffeine.entity.brain;

import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.World;

public class LeftBrain extends Brain {
  protected Dir dir = Dir.DOWN;
  protected final double turnThresh = .99;
  protected boolean lastFailed = false;

  public LeftBrain(Entity self) {
    super(self);
  }

  public static Entity embody(World world) {
    return new LeftBrain(new Entity(world)).getEntity();
  }

  @Override
  public void tick() {
    if (turnThresh < Math.random() || lastFailed)
      dir = dir.prev();
    new Face(dir).performBy(self);
    lastFailed = !new Move(dir).performBy(self);
  }
}
