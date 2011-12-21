package caffeine.world.tile;

import java.awt.Color;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Warp extends TileObject {
	public int mapID = 0;
	public Location destination;

	public Warp(Location l){
		destination = l;
	}
	@Override
	public boolean pass() {
		return true;
	}

	@Override
	public boolean safe() {
		return true;
	}

	@Override
	public Color color() {
		return Color.ORANGE;
	}
	public void warp(Object o){
		if(o instanceof Entity){
			Entity e = (Entity) o;
			e.loc(destination);
		}
	}

	@Override
	public String toString(){
		return "@";
	}

}
