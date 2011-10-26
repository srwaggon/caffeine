package caffeine.world;

import java.awt.*;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.view.Sprite;
import caffeine.view.Sprited;


public class Tile implements Sprited{
	protected ArrayList<Entity> occupants = new ArrayList<Entity>();
	static protected int size = 32;
	protected Location loc;
	
	private TileObject type;
	Tile(Location l){
		this.loc = l;
		this.type = null;
	}
	
	Tile(Location l, int size, TileObject t){
		this.loc = l;
		Tile.size = size;
		this.type = t;
	}

	// ACCESSORS
	public int getSize(){
		return size;
	}
	
	public boolean isBlocked(){
		return type.isBlocked();
	}
	public boolean isEmpty(){
		return occupants.isEmpty();
	}
	public boolean isSafe(){
		return type.isSafe();
	}

	
	public Sprite getSprite(){
		return new Sprite(type.getColor(), new Rectangle(loc.getX(), loc.getY(), size, size));
	}
	
	public TileObject getType(){
		return type;
	}
	
	public String description(){
		return type + "@(" + loc.getX() + "," + loc.getY() + ")";
	}
	public String toString(){
		return type.toString();
	}
	public ArrayList<Entity> getOccupants(){
		return occupants;
	}
	// MUTATORS
	/*
      Returns whether or not setting the character was successful.
	 */
	public synchronized void addOccupant(Entity e){
		occupants.add(e);
	}
	public synchronized void removeOccupant(Entity e){
		occupants.remove(e);
	}
	public void setSize(int size){
		if (size > 0){
			Tile.size = size;
		}
	}
	public void setGrass(){
		type = new Grass();
	}
	public void setWall(){
		type = new Wall();
	}
	public void setWater(){
		type = new Water();
	}
	public void setWarp(Warp w){
		type = w;
	}

	public void read(char c){
		if(c == '#'){
			this.setWall();
		}else if(c == '~'){
			this.setWater();
		}else{
			this.setGrass();
		}
	}
	@Override
	public Location getLoc() {
		// TODO Auto-generated method stub
		return null;
	}
}