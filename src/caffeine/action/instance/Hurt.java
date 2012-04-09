package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;

public class Hurt implements Action {
  int amt;
  Actor sender = null;
  
  public Hurt(int amount, Actor sender) {
    amt = amount;
    this.sender = sender;
  }
  
  public void performBy(Actor performer) {
    performer.health -= amt;
    System.out.println(performer + " hurt by " + sender);
    
    if (performer.health <= 0) {
      new Die(sender.toString()).performBy(performer);
    }
  }
  
}
