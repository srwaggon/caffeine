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

	/**
	 * Determines if a move is worth processing, and if so,
	 * combines its angle with the mover's speed to create
	 * a motion vector, that gets added to velocity.
	 */
	public void handleMove(Move m, Actor performer){
		Vector motionVector = new Vector(m.angle, speed);
		/*
		for (Location l : performer.bounds()){
			Location p = l.project(motionVector);
			//System.out.println(p + " : " + p.tile() + p.tile().pass());
			if (!p.tile().pass()){
				return;
			}
		}
		 */
		velocity.add(motionVector);

		//loc.tile().remove(performer);
		//loc.tile().add(performer);
	}



	public void tick(){
		loc.add(velocity);
		velocity.magnitude(0);
	}
}
