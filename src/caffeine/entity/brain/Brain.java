package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.entity.Actor;

public class Brain {

	public ArrayList<Action> next(Actor actor){
		ArrayList<Action> a = new ArrayList<Action>();
		a.add(Action.Inaction);
		return a;
	}
}
