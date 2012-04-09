package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.entity.instance.Projectile;

public class CreateProjectile implements Action {
  
  public void performBy(Actor performer) {
    new Projectile(performer);
  }
  
  public String toString() {
    return "projectile creation";
  }
}
