package caffeine.world;

import java.awt.*;
import java.util.ArrayList;

import caffeine.entity.Entity;

public class Tile{
	protected ArrayList<Entity> occupants = new ArrayList<Entity>();
	static protected int size = 32;
	private TileObject type;
	
	public Tile(){
		this.type = new Grass();
	}
	
	public Tile(TileObject t){
		this.type = t;
	}

	// ACCESSORS
	public int size(){
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
	
	public Shape shape(){
		return new Rectangle(0, 0, size, size);
	}
	
	public TileObject getType(){
		return type;
	}
	
	public Tile next(Direction dir){
		// TODO 
		Tile next = this;
		switch(dir){
		case NORTH: break;
		case EAST: break;
		case SOUTH: break;
		case WEST: break;
		default: break;
		}
		return next;
	}

	public String toString(){
		return type.toString();
	}
	
	public ArrayList<Entity> entities(){
		return occupants;
	}
	
	// MUTATORS
	public synchronized void add(Entity e){occupants.add(e);}
	
	public synchronized void remove(Entity e){occupants.remove(e);}
	
	public static void setSize(int size){
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
}