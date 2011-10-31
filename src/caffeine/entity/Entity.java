package caffeine.entity;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.rule.Rule;
import caffeine.util.Util;
import caffeine.view.Sprite;
import caffeine.view.Sprited;
import caffeine.world.Direction;
import caffeine.world.Location;
import caffeine.world.Tile;

public class Entity implements Sprited{
	protected boolean isAlive = true;
	protected Brain brain;
	protected Color color = Util.randomColor();
	protected Direction direction;
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected int speed = width/4;
	protected Location loc;
	protected String name;

	public Entity(){
		this(new Location());
	}
	public Entity(Location loc){
		brain = new LeftBrain();
		id = numCharacters++;
		this.loc = loc;
		name = "" + id;
		System.err.println("Spawning Entity " + name);
	}
	
	public boolean alive(){return isAlive;}

	public ArrayList<Location> bounds(){
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
	
	public Location center(){
		return loc.add(width/2, height/2);
	}
	
	public void color(Color c){this.color = c;}
	
	public void die(){
		isAlive = false;
		tile().remove(this);
		System.err.println(this + " has died.");
	}
	
	public Direction facing() {return direction;}
	
	public void facing(Direction angle) {this.direction = angle;}

	public Location loc(){return loc;}
	
	public void loc(Location loc){
		tile().remove(this);
		this.loc = loc;
		tile().add(this);
	}
	
	public ArrayList<Action> next(){return brain.next(this);}

	public int speed(){return speed;}

	@Override
	public Sprite sprite(){
		// TODO System.out.println("Sprite " + this);
		return new Sprite(color, loc,
				new RoundRectangle2D.Double(
						loc.x, loc.y, width, height, 15, 15));
	}

	public void tick(){
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
		}
		if(isAlive){
			for(Action a : next()){
				a.performOn(this);	
			}
		}
	}

	public Tile tile(){return loc.tile();}
	
	@Override
	public String toString(){
		return name + "@" + loc.toString(); 
	}

	public int x(){return loc.x;}

	public int y(){return loc.y;}
}