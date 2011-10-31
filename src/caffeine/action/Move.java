package caffeine.action;

import java.util.ArrayList;

import caffeine.Rules;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Location;


public class Move implements Action {
	private Direction direction;
	
	public Move(Direction dir){
		direction = dir;
	}
	
	public ArrayList<Location> projectVertices(Entity e){
		ArrayList<Location> projection = new ArrayList<Location>();
		for(Location l : e.bounds()){
			projection.add(l.project(direction, e.speed()));
		}
		return projection;
	}

	public void performOn(Object o){
		if(o instanceof Entity){
			Entity e = (Entity) o;
			if(Rules.checkValidMove(this, e)){
				e.loc(e.loc().project(direction, e.speed()));
			}
		}
	}
	
	public String toString(){
		return "move: " + direction;
	}
}
