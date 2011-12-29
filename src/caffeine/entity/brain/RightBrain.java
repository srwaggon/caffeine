package caffeine.entity.brain;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.util.Angle;

public class RightBrain extends Brain {
	Angle theta = new Angle();

	public ArrayList<Action> next(Actor a) {
		ArrayList<Action> actions = new ArrayList<Action>();
		Move m = new Move(theta);
		if(a.motion().validMove(m, a)) {
			actions.add(m);
		}else{
			theta.add(45);
		}
		return actions;
	}

}
