package caffeine.action;

import caffeine.entity.Entity;


public class Attack implements Action{


  public boolean performBy(Entity performer) {
    performer.getMap();
    return true;
  }

}
