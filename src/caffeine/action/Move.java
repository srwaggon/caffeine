package caffeine.action;

import caffeine.entity.Actor;
import caffeine.util.Vector;

public class Move implements Action {
	Vector motionVector;
	
	public Move(Vector motionVector){
		this.motionVector = motionVector;	
	}
	
	public void performOn(Actor actor) {
		actor.loc().tile().remove(actor);

		// compound new motion with old motion
		actor.loc().add(motionVector);		

		actor.loc().tile().add(actor);
	}

}
