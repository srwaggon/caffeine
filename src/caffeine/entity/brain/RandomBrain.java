package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Accelerate;
import caffeine.action.Action;
import caffeine.entity.Actor;
import caffeine.util.Angle;

public class RandomBrain extends Brain {
	@Override
	public ArrayList<Action> next(Actor actor) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		actionList.add(new Accelerate(Angle.random()));
		return actionList;
	}

}
