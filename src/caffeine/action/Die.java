package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;

public class Die implements Action {
	String cause;
	public Die(String cause){
		this.cause = cause;
	}
	
	/**
	 * 
	 */
	public void perform(Actor performer, Entity target) {
		perform(performer);
	}

	public void perform(Actor performer){
		performer.tile().remove(performer);
		performer.alive(false);
		System.err.println(performer + " has died from " + cause);
	}
}
