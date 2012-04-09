package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;

public class Attack implements Action {
  
  public void performBy(Actor performer) {
    int attack = performer.attack;
    int attackRadius = performer.radius() * 2;
    Direction facing = performer.motion().facing();
    
    Loc projection = performer.loc().copy();
    projection.translate(facing, attackRadius);
    
    for (Entity e : projection.tile().occupants()) {
      if (e instanceof Actor) {
        Actor a = (Actor) e;
        if (!a.equals(performer)
            && a.loc().distanceTo(performer.loc()) < attackRadius) {
          System.out.println(performer + " attacking " + a);
          new Hurt(attack, performer).performBy(a);
        }
      }
    }
    
  }
  
}
