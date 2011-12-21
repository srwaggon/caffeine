package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.util.Vector;

/**
 * Represents a change-in-location action which can be performed on (by) Actor objects.
 * @author srwaggon
 *
 */
public class KineticMove implements Action {
	Vector motionVector;

	/**
	 * Creates a move object
	 * @param motionVector a vector indicating the direction and magnitude of movement
	 */
	public KineticMove(Vector motionVector){
		this.motionVector = motionVector;
	}

	public void perform(Actor performer, Entity target) {
		perform(performer); // hand off to the singly-parameterized method
	}

	public void perform(Actor performer){
		performer.loc().tile().remove(performer);
		performer.loc().add(motionVector);
		performer.loc().tile().add(performer);
	}

}
