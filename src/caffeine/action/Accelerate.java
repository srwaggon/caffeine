package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.util.Angle;
import caffeine.util.Vector;


public class Accelerate implements Action {
	protected Angle theta;
	
	public Accelerate(Angle theta){
		this.theta = theta;
	}
	
	public void perform(Actor actor, Entity target){
		Vector acceleration = new Vector(theta, actor.accelRate());
		actor.velocity().add(acceleration);
	}
	
	public String toString(){
		return "move: " + theta;
	}
}
