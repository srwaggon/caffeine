package caffeine.action;

import caffeine.entity.Entity;

public class Activate implements Action {

  @Override
  public boolean performBy(Entity performer) {
    return false;
  }

}
