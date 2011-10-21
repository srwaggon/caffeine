package caffeine.action;

import java.util.ArrayList;

import caffeine.Rules;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Point;


public class Move implements Action {
	private Direction direction;
	
	public Move(Direction dir){
		this.direction = dir;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public ArrayList<Point> projectVertices(Entity e){
		ArrayList<Point> projection = new ArrayList<Point>();
		for(Point p : e.getVertices()){
			projection.add(p.project(direction, e.getSpeed()));
		}
		return projection;
	}

	public void performOn(Object o){
		if(o instanceof Entity){
			Entity e = (Entity) o;
			if(Rules.checkValidMove(this, e)){
				e.setLoc(e.getLoc().project(direction, e.getSpeed()));
			}
			if(Rules.checkDeadlyMove(this, e)){
				e.die();
			}
		}
	}
	
	public String toString(){
		return "move: " + direction;
	}
}
