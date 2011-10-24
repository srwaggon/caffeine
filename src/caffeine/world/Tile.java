package caffeine.world;

import java.awt.*;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.view.Sprite;


public class Tile{
	protected ArrayList<Entity> occupants = new ArrayList<Entity>();
	static protected int size = 32;
	protected int x, y;
	private TileObject type;
	Tile(int x, int y){
		this.x = x;
		this.y = y;
		this.type = null;
	}
	Tile(int x, int y, int size, TileObject t){
		this.x = x;
		this.y = y;
		Tile.size = size;
		this.type = t;
	}

	// ACCESSORS
	public int getSize(){
		return size;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Point getPoint(){
		return new Point(x*size, y*size);
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
	/*
	public Shape getImg(){
		g.setColor(type.getColor());
		g.fillRect(x*size, y*size, size, size);
		g.setColor(Color.BLACK);
		g.drawLine(x*size, y*size, (x+1)*size-1, y*size);
		g.drawLine(x*size, y*size, x*size, (y+1)*size-1);
	}
	*/
	
	public Sprite getSprite(){
		return new Sprite(type.getColor(), new Rectangle(x*size, y*size, size, size));
	}

	public String description(){
		return type + "@(" + x + "," + y + ")";
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