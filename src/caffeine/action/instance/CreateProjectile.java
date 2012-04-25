package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Entity;
import caffeine.entity.instance.Projectile;

public class CreateProjectile implements Action {
  
  public void performBy(Entity performer) {
    new Projectile(performer);
  }
  
  public String toString() {
    return "projectile creation";
  }
}
