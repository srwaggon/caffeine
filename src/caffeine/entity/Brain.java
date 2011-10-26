package caffeine.entity;

import caffeine.action.Action;

public class Brain {

	public Action next(Entity e){
		return Action.Inaction;
	}
}
