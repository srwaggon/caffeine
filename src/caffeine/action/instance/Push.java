package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.entity.Entity;

public class Push implements Action {
  protected Entity pushee;
  
  public Push(Entity who) {
    pushee = who;
  }
  
  public void perform(Actor performer) {
    
  }
  
}
