package caffeine;

import java.util.ArrayList;

import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.world.Location;


public class Rules {
	public static boolean checkValidMove(Move move, Actor a){
		ArrayList<Location> vertices = move.projectVertices(a);
		for(Location l : vertices){
			if (!l.legal() || l.tile().isBlocked()){
				return false;
			}
		}
		return true;
	}
}
