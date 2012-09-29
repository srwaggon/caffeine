package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.Map;

public class LeftBrain extends Brain {
  protected Dir dir = Dir.DOWN;
  protected final double turnThresh = .99;
  protected boolean lastFailed = false;
  private int timetick = 0;

  public LeftBrain(Mob self) {
    super(self);
  }

  public static Mob embody(Map map) {
    return new LeftBrain(new Mob(map)).getSelf();
  }

  @Override
  public void tick() {
    if (turnThresh < Math.random() || lastFailed)
      dir = dir.prev();
    self.setDir(dir);
    if((timetick++ & 0x1) == 0)
      lastFailed = !self.move(dir);
  }
}
