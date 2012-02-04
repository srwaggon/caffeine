package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.world.Direction;


public class Attack implements Action {


  public void perform(Actor performer) {
    int attack = performer.attack;
    int attackRadius = performer.radius()*2;
    Direction facing =  performer.motion().facing();

    for(Entity e : performer.loc().project(facing, attackRadius).tile().occupants()) {
      if(e instanceof Actor){
        Actor a = (Actor)e;
        if(!a.equals(performer) && a.loc().distanceTo(performer.loc()) < attackRadius){
          System.out.println(performer + " attacking " + a);
          new Hurt(attack, performer).perform(a);
        }
      }
    }

  }

}
