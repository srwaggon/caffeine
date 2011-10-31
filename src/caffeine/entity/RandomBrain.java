package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.world.Direction;

public class RandomBrain extends Brain {
	@Override
	public ArrayList<Action> next(Entity e) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		actionList.add(new Move(Direction.random()));
		return actionList;
	}

}
