package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Entity;

public class Hurt implements Action {
  int amt;
  Entity sender = null;
  
  public Hurt(int amount, Entity sender) {
    amt = amount;
    this.sender = sender;
  }
  
  public void performBy(Entity performer) {
    performer.health -= amt;
    System.out.println(performer + " hurt by " + sender);
    
    if (performer.health <= 0) {
      new Die(sender.toString()).performBy(performer);
    }
  }
  
}
