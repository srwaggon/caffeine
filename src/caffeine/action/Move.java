package caffeine.action;

import java.util.ArrayList;

import caffeine.entity.Actor;
import caffeine.util.Angle;
import caffeine.util.Vector;
import caffeine.world.Location;


public class Move implements Action {
	protected Angle theta;
	
	public Move(Angle theta){
		this.theta = theta;
	}
	
	public ArrayList<Location> projectVertices(Actor a){
		ArrayList<Location> projection = new ArrayList<Location>();
		/*
		for(Location l : a.bounds()){
			projection.add(l.project(new Vector(theta, a.speed())));
		}
		*/
		return projection;
	}

	public void performOn(Actor actor){
		Vector v = new Vector(theta, actor.acceleration());
		actor.velocity().add(v);
	}
	
	public String toString(){
		return "move: " + theta;
	}
}
