package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Accelerate;
import caffeine.util.Angle;

public class RandomBrain extends Brain {
	@Override
	public ArrayList<Action> next(Actor actor) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		actionList.add(new Accelerate(Angle.random()));
		return actionList;
	}

}
