package caffeine.action;

import caffeine.entity.Entity;

public class Push implements Action {
  protected Entity pushee;
  protected Move trialMove;

  public Push(Move move, Entity who) {
    pushee = who;
    trialMove = move;
  }

  @Override
  public boolean performBy(Entity performer) {
    if (trialMove.dryRun(pushee)) {
      boolean result = trialMove.performBy(pushee);
      if (result) {
        pushee.actionPlans.clear();
        return true;
      }
    }
    return false;
  }

}
