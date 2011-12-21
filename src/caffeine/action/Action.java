package caffeine.action;

import caffeine.entity.Actor;

/* Represents a general action to be performed by an Actor */
public interface Action {

	// Could require a 'target' in constructor

	/* Required methods */
	void perform(Actor performer);


	/* An example/basic action: Inaction! */
	public static Action Inaction = new Action(){
		public void perform(Actor performer) {
			// Do nothing
		}
	};
}
