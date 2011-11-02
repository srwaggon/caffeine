package caffeine.entity;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import caffeine.Game;
import caffeine.rule.Rule;
import caffeine.util.Util;
import caffeine.view.Sprite;
import caffeine.view.Sprited;
import caffeine.world.Location;
import caffeine.world.Tile;

public class Entity implements Sprited{
	protected boolean isAlive = true;
	protected Color color = Util.randomColor();
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected Location loc;
	protected String name;

	public Entity(){
		this(new Location());
	}
	public Entity(Location loc){
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
	
	
	public Location loc(){return loc;}
	
	public void loc(Location loc){
		tile().remove(this);
		this.loc = loc;
		tile().add(this);
	}

	@Override
	public Sprite sprite(){
		return new Sprite(color, loc,
				new RoundRectangle2D.Double(
						loc.x, loc.y, width, height, 15, 15));
	}

	public void tick(){
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
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