package caffeine.action;

import caffeine.entity.Entity;

public class Push implements Action {
  protected Entity pushee;

  public Push(Entity who) {
    pushee = who;
  }

  @Override
  public boolean performBy(Entity performer) {
    pushee.actionPlans.clear();
    double xa = performer.getDirection().dx();
    double ya = performer.getDirection().dy();
    pushee.move(xa, ya, true);
    return true;
  }
}
