package caffeine.world;

import java.awt.Color;

import caffeine.entity.Entity;

public class Warp extends TileObject {
	public int mapID = 0;
	public Point loc;
	
	public Warp(int mapID, int x, int y){
		this.mapID = mapID;
		loc = new Point(x, y);
	}
	@Override
	public boolean isBlocked() {
		return false;
	}

	@Override
	public boolean isSafe() {
		return true;
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}
	public void warp(Object o){
		if(o instanceof Entity){
			Entity e = (Entity) o;
			e.setLoc(loc);
		}
	}
	
	@Override
	public String toString(){
		return "@";
	}

}
