package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;

public class Brain {

	public ArrayList<Action> next(Entity e){
		ArrayList<Action> a = new ArrayList<Action>();
		a.add(Action.Inaction);
		return a;
	}
}
