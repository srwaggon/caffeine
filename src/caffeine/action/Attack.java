package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.util.Angle;
import caffeine.util.Vector;

public class Attack implements Action {

	@Override
	public void perform(Actor performer) {
		int attack = performer.attack;
		int attackRadius = performer.attackRadius;
		Angle facing =  performer.motion().velocity().angle();
		Vector attackVector = new Vector(facing, attackRadius);
		for(Entity e : performer.loc().add(attackVector).tile().entities()) {
			if(e instanceof Actor){
				Actor a = (Actor)e;
				if(!a.equals(performer) && a.loc().distanceTo(performer.loc()) < attackRadius){
					System.err.println(performer + " attacking " + a);
					new Hurt(attack, performer).perform(a);
				}
			}
		}

	}

}
