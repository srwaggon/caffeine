package caffeine.world;

import caffeine.Game;
import caffeine.util.Angle;
import caffeine.util.Vector;
import caffeine.world.tile.Tile;

public class Location {
	public int mapID;
	public int x;
	public int y;

	public Location(){
		mapID = 0;
		x = 0;
		y = 0;
	}

	public Location(int mapID, int x, int y){
		this.mapID = mapID;
		this.x = x;
		this.y = y;
	}

	public Map map(){
		return Game.instance().world().get(mapID);
	}

	public Tile tile(){
		return map().getTileAt(x, y);
	}

	public Location add(int xOffset, int yOffset){
		return new Location(mapID, x+xOffset, y+yOffset);
	}

	public Location project(Vector v){
		Angle a = v.angle();
		double dx = Math.cos(a.radians());
		double dy = Math.sin(a.radians());
		return new Location(mapID, (int)dx+x, (int)dy+y);
	}

	public String toString(){
		return "[" + mapID + "(" + x + "," + y + ")]";
	}

	public boolean legal() {
		return map().withinBounds(x,  y);
	}

	public void add(Vector velocity) {
		double r = velocity.angle().radians();
		double speed = velocity.magnitude();
		double dx = Math.cos(r) * speed;
		double dy = Math.sin(r) * speed;
		x += dx;
		y += dy;
	}

	public Location copy() {
		return new Location(mapID, x, y);
	}
}
