package caffeine.entity;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.rule.Rule;
import caffeine.util.Utilities;
import caffeine.view.Sprite;
import caffeine.view.Sprited;
import caffeine.world.Direction;
import caffeine.world.Location;
import caffeine.world.Tile;

public class Entity implements Sprited{
	protected boolean isAlive = true;
	protected Brain brain;
	protected Color color = Utilities.randomColor();
	protected Direction facing = Direction.NORTH;
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected int speed = width/2;
	protected Location loc;
	protected String name;

	public Entity(){
		this(new Location());
	}
	public Entity(Location loc){
		brain = new Brain();
		id = numCharacters++;
		this.loc = loc;
		name = "" + id;
		System.err.println("Spawning Entity " + name);
	}
	
	public void die(){
		isAlive = false;
		System.err.println(this + " has died.");
	}

	public Direction facing() {return facing;}
	
	public Location center(){
		return loc.add(width/2, height/2);
	}
	
	public Location loc(){return loc;}
	
	public Action next(){return brain.next(this);}
	
	public int speed(){return speed;}
	
	public Tile tile(){return loc.tile();}

	// TODO revise.  strongly.
	public ArrayList<Location> getBounds(){
		int id = loc.mapID;
		int x = x();
		int y = y();
		ArrayList<Location> vertices = new ArrayList<Location>();
		vertices.add(new Location(id, x, y));
		vertices.add(new Location(id, x + width - 1, y));
		vertices.add(new Location(id, x, y + height - 1));
		vertices.add(new Location(id, x + width - 1, y + height - 1));
		return vertices;
	}
	
	public int x(){return loc.x;}
	
	public int y(){return loc.y;}

	public boolean isAlive(){return isAlive;}

	public void setLoc(Location loc){this.loc = loc;}

	public Sprite getSprite(){
		return new Sprite(color, loc,
				new RoundRectangle2D.Double(
						loc.x, loc.y, width, height, 15, 15));
	}

	public void setColor(Color c){
		this.color = c;
	}
	public void setFacing(Direction facing) {
		this.facing = facing;
	}

	public void tick(){
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
		}
		if(isAlive){
			next().performOn(this);
		}
	}

	@Override
	public String toString(){
		return name + "@" + loc.toString(); 
	}
}