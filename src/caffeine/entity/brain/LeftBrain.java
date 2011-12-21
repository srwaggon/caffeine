package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.KineticMove;
import caffeine.entity.Actor;
import caffeine.util.Angle;
import caffeine.util.Util;
import caffeine.util.Vector;

public class LeftBrain extends Brain {
	Angle theta = new Angle();

	public ArrayList<Action> next(Actor a) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(Util.coinFlip()){

		}
		theta.add(10);
		actions.add(new KineticMove(new Vector(theta, 4)));
		return actions;
	}

}
