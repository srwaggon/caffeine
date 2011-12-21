package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.util.Angle;
import caffeine.util.Vector;

public class StaticMove implements Action {
	Angle angle;
	public StaticMove(Angle theta){
		angle = theta;
	}
	
	public void perform(Actor performer, Entity target) {
		Vector motionVector = new Vector(angle, performer.speed());
		performer.loc().tile().remove(performer);
		performer.loc().add(motionVector);
		performer.loc().tile().add(performer);
	}

}
