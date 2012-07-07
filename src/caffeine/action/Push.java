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
    return true;
  }
}
