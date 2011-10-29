package caffeine.world;

import caffeine.Game;

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
	
	public Location project(Direction direction, int speed){
		int dx = 0;
		int dy = 0;
		switch(direction){
		case WEST:
			dx = -1; break;
		case NORTH:
			dy = -1; break; 
		case EAST:
			dx = 1; break;
		case SOUTH:
			dy = 1; break;
		default:
			break;
		}
		dx *= speed;
		dy *= speed;
		return new Location(mapID, x + dx, y + dy);
	}
	public String toString(){
		return "[" + mapID + "(" + x + "," + y + ")]";
	}

	public boolean legal() {
		return map().withinBounds(x,  y);
	}
}
