package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.Dir;

public class LeftBrain extends Brain {
  protected Dir dir = Dir.S;
  protected final double turnThresh = .99;
  protected boolean lastFailed = false;
  private int timetick = 0;

  public LeftBrain(Mob self) {
    super(self);
  }

  @Override
  public void tick() {
    if (turnThresh < Math.random() || lastFailed)
      dir = dir.prev();
    self.setDir(dir);
    if((timetick++ & 0x1) == 0)
      lastFailed = !self.moveDirInSpeed(dir);
  }
}
