package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.action.TurnLeft;
import caffeine.util.Util;

public class LeftBrain extends Brain {

	@Override
	// TODO implement
	public ArrayList<Action> next(Entity e) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(Util.coinFlip()){
			actions.add(new TurnLeft());
		}
		actions.add(new Move(e.facing()));
		return actions;
	}

}
