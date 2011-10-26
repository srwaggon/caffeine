package caffeine.entity;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.world.Direction;

public class RandomBrain extends Brain {
	@Override
	public Action next(Entity e) {
		return new Move(Direction.getRandom());
	}

}
