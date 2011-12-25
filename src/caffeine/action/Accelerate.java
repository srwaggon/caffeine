package caffeine.action;

import caffeine.entity.Actor;
import caffeine.util.Angle;


public class Accelerate implements Action {
	protected Angle theta;

	public Accelerate(Angle theta){
		this.theta = theta;
	}

	public void perform(Actor actor){
		actor.motion().handleAcceleration(this,  actor);
	}

	public String toString(){
		return "move: " + theta;
	}
}
