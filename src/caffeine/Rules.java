package caffeine;

import java.util.ArrayList;

import caffeine.action.Accelerate;
import caffeine.entity.Actor;
import caffeine.world.Location;


public class Rules {
	public static boolean checkValidMove(Accelerate move, Actor a){
		ArrayList<Location> vertices = move.projectVertices(a);
		for(Location l : vertices){
			if (!l.legal() || l.tile().isBlocked()){
				return false;
			}
		}
		return true;
	}
}
