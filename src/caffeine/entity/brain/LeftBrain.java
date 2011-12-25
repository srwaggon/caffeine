package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.util.Angle;

public class LeftBrain extends Brain {
	Angle theta = new Angle();

	public ArrayList<Action> next(Actor a) {
		ArrayList<Action> actions = new ArrayList<Action>();
		theta.add(10);
		actions.add(new Move(theta));
		return actions;
	}

}
