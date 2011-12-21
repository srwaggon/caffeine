package caffeine.action;

import caffeine.entity.Actor;
import caffeine.util.Angle;

/**
 * Represents a change-in-location action which can be performed on (by) Actor objects.
 * @author srwaggon
 *
 */
public class Move implements Action {
	public Angle angle;

	/**
	 * Creates a move object
	 * @param motionVector a vector indicating the direction and magnitude of movement
	 */
	public Move(Angle theta){
		angle = theta;
	}

	public void perform(Actor performer){
		performer.motion().handleMove(this, performer);
	}

}
