package caffeine.action;

import caffeine.entity.Actor;
import caffeine.util.Vector;
import caffeine.world.Location;

public class StaticMotion implements Motion{
	protected Location loc;
	public double speed = 4;
	Vector velocity = new Vector();
	double accelerationRate = 0;

	public StaticMotion(Location l){
		loc = l;
	}

	public void handleAcceleration(Accelerate a, Actor target){
		; // Do nothing -- static motion
	}

	public void handleMove(Move m, Actor performer){
		if(validMove(m, performer)){
			velocity.add(new Vector(m.angle(), speed));
		}
	}


	public boolean validMove(Move m, Actor performer){
		Vector motionVector = new Vector(m.angle(), speed);

		return true;
	}

	public void tick(){
		loc.set(loc.add(velocity));
		velocity.magnitude(0);
	}

	@Override
	public Vector velocity() {
		return velocity;
	}
}
