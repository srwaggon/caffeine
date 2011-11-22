package caffeine.world;

import java.awt.*;
import java.util.ArrayList;

import caffeine.entity.Entity;

public class Tile{
	protected ArrayList<Entity> occupants = new ArrayList<Entity>();
	static protected int size = 32;
	public static void setSize(int size){
		if (size > 0){
			Tile.size = size;
		}
	}
	
	private TileObject type;
	
	public Tile(){this.type = new Grass();}

	public Tile(char c){
		if(c == '#'){
			this.setWall();
		}else if(c == '!'){
			this.setLava();
		}else if(c == '-'){
			this.setIce();
		}else{
			this.setGrass();
		}
	}
	
	public Tile(TileObject t){this.type = t;}
	
	public void add(Entity e){occupants.add(e);}
	
	public ArrayList<Entity> entities(){return occupants;}
	
	public double drag(){return type.drag();}
	
	public TileObject getType(){return type;}
	
	public boolean isBlocked(){return type.isBlocked();}
	
	public boolean isEmpty(){return occupants.isEmpty();}

	public boolean isSafe(){return type.isSafe();}
	
	public void remove(Entity e){
		occupants.remove(e);
	}
	
	public void setGrass(){type = new Grass();}
	
	public void setIce(){type = new Ice();}
	
	public void setWall(){type = new Wall();}
	
	public void setWarp(Warp w){type = w;}
	
	public void setLava(){type = new Lava();}
	
	public Shape shape(){return new Rectangle(0, 0, size, size);}
	
	public int size(){return size;}
	
	public String toString(){return type.toString();}
}