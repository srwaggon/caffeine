package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;

public class Attack implements Action {
  
  public void performBy(Entity performer) {
    int attack = performer.attack;
    int attackRadius = performer.radius() * 2;
    Direction facing = performer.motion().facing();
    
    Loc projection = performer.loc().copy();
    projection.translate(facing, attackRadius);
    
    for (Entity e : projection.tile().occupants()) {
      if (!e.equals(performer)
          && e.loc().distanceTo(performer.loc()) < attackRadius) {
        System.out.println(performer + " attacking " + e);
        new Hurt(attack, performer).performBy(e);
      }
    }
  }
  
}
