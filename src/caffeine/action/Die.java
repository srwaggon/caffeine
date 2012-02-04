package caffeine.action;

import caffeine.entity.Actor;

public class Die implements Action {
  String cause;
  public Die(String cause){
    this.cause = cause;
  }

  public void perform(Actor performer){
    performer.alive(false);
    performer.tile().remove(performer);
    System.out.println(performer + " has died from " + cause);
  }
}
