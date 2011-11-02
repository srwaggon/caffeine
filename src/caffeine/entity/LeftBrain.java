package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.util.Angle;
import caffeine.util.Util;

public class LeftBrain extends Brain {
	Angle theta = new Angle();
	
	public ArrayList<Action> next(Actor a) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(Util.coinFlip()){
			theta.add(30);
		}
		actions.add(new Move(theta));
		return actions;
	}

}
