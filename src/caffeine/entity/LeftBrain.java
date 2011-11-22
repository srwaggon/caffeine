package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Accelerate;
import caffeine.util.Angle;
import caffeine.util.Util;

public class LeftBrain extends Brain {
	Angle theta = new Angle();
	
	public ArrayList<Action> next(Actor a) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(Util.coinFlip()){
					
		}
		theta.add(10);
		actions.add(new Accelerate(theta));
		return actions;
	}

}
