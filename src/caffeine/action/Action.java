package caffeine.action;

import caffeine.entity.Actor;

public interface Action {
	public static Action Inaction = new Action(){	
		@Override
		public void performOn(Actor a) {
			// Do nothing
		}
	};
		
	void performOn(Actor a);
}
