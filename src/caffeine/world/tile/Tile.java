package caffeine.world.tile;

import java.awt.Rectangle;
import java.awt.Shape;
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

	public Tile(){type = new Grass();}

	public Tile(char c){
		if(c == '#'){
			setRock();
		}else if(c == '!'){
			setLava();
		}else if(c == '-'){
			setIce();
		}else{
			setGrass();
		}
	}

	public Tile(TileObject t){type = t;}

	public void add(Entity e){occupants.add(e);}

	public ArrayList<Entity> entities(){return occupants;}

	public double drag(){return type.drag();}

	public TileObject getType(){return type;}

	public boolean pass(){return type.pass();}

	public boolean isEmpty(){return occupants.isEmpty();}

	public boolean safe(){return type.safe();}

	public void remove(Entity e){
		occupants.remove(e);
	}

	public void setGrass(){type = new Grass();}

	public void setIce(){type = new Ice();}

	public void setRock(){type = new Rock();}

	public void setWarp(Warp w){type = w;}

	public void setLava(){type = new Lava();}

	public Shape shape(){return new Rectangle(0, 0, size, size);}

	public static int size(){return size;}

	public String toString(){return type.toString();}
}