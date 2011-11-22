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

		// calculate friction
		double totalDrag = actor.drag() + actor.loc().tile().drag();
		double friction = motionVector.magnitude()*totalDrag;
		System.out.println(totalDrag);
		//System.out.println(friction + "\t" + motionVector.magnitude());
		
		double speed = motionVector.magnitude() - friction;
		speed = speed < 1 ? 0 : speed;
		
		// apply friction
		actor.velocity().magnitude(speed);
		
		// apply motion
		actor.loc().add(motionVector);
		

		actor.loc().tile().add(actor);
	}

}
