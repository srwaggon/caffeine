package caffeine.world;

import java.awt.Color;

import caffeine.entity.Entity;

public class Warp extends TileObject {
	public int mapID = 0;
	public Location destination;
	
	public Warp(Location l){
		destination = l;
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
			e.setLoc(destination);
		}
	}
	
	@Override
	public String toString(){
		return "@";
	}

}
