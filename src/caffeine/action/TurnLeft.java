package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class TurnLeft implements Action {

	@Override
	public void performOn(Object o) {
		if(o instanceof Entity){
			Entity e = (Entity) o;
			switch(e.facing()){
			case NORTH: e.setFacing(Direction.WEST); break;
			case WEST: e.setFacing(Direction.SOUTH); break;
			case EAST: e.setFacing(Direction.NORTH); break;
			case SOUTH: e.setFacing(Direction.EAST); break;
			default: e.setFacing(Direction.NORTH);
			}
		}
	}

}
