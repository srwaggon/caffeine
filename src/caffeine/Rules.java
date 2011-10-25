package caffeine;

import java.util.ArrayList;

import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Map;
import caffeine.world.Point;
import caffeine.world.Warp;


public class Rules {
	public static boolean checkValidMove(Move m, Entity e){
		Map b = Game.getInstance().getCurrentMap();
		ArrayList<Point> vertices = m.projectVertices(e);
		for(Point p : vertices){
			if (!b.withinBounds(p) || b.getTileAt(p).isBlocked()){
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkDeadlyMove(Move m, Entity e){
		return !Game.getInstance().getCurrentMap().getTileAt(e.getCenter()).isSafe();
	}
	
	public static boolean checkWarpMove(Move m, Entity e){
		return Game.getInstance().getCurrentMap().getTileAt(e.getCenter()).getType() instanceof Warp;
	}
}
