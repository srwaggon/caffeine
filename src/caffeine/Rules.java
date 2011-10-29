package caffeine;

import java.util.ArrayList;

import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Location;


public class Rules {
	public static boolean checkValidMove(Move move, Entity e){
		ArrayList<Location> vertices = move.projectVertices(e);
		for(Location l : vertices){
			if (!l.legal() || l.tile().isBlocked()){
				return false;
			}
		}
		return true;
	}
}
