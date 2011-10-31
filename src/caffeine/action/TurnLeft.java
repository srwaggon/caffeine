package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class TurnLeft implements Action {

	@Override
	public void performOn(Object o) {
		if(o instanceof Entity){
			Entity e = (Entity) o;
			e.facing(new Direction(e.facing().theta()- 90));
		}
	}
}
