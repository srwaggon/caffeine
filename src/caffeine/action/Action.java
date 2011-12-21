package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;

public interface Action {
	public static Action Inaction = new Action(){
		@Override
		public void perform(Actor performer, Entity target) {
			// Do nothing
		}
	};

	void perform(Actor performer, Entity target);
}
