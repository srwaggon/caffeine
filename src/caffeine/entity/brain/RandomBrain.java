package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.util.Angle;
import caffeine.util.Util;

public class RandomBrain extends Brain {
	Angle theta = Angle.random();
	public ArrayList<Action> next(Actor actor) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		if(Util.coinflip()) {
			theta = Angle.random();
			//System.out.println(theta);
		}
		actionList.add(new Move(theta));
		return actionList;
	}

}
