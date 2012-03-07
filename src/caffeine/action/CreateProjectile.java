package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.instance.Projectile;

public class CreateProjectile implements Action {
  
  public void perform(Actor performer) {
    new Projectile(performer);
  }
  
  public String toString() {
    return "projectile creation";
  }
}
