package caffeine.entity;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import caffeine.util.Util;
import caffeine.view.Sprite;
import caffeine.view.Sprited;
import caffeine.world.Location;
import caffeine.world.tile.Tile;


/**
 * base class representing objects in the world.  Entities have a location
 * @author srwaggon
 *
 */
public class Entity implements Sprited{
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected Location loc;
	protected Sprite sprite;
	protected String name;

	public Entity(){
		this(new Location());
	}

	public Entity(Location location){
		id = numCharacters++;
		loc = location.copy();
		sprite = new Sprite(Util.randomColor(), loc,
				new RoundRectangle2D.Double(
						loc.x, loc.y, width, height, 16, 16));
		name = "" + id;
		System.err.println("Spawning Entity " + name);
	}

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

	public void color(Color c){sprite.color(c);}


	public Location loc(){return loc;}

	public void loc(Location loc){
		tile().remove(this);
		this.loc = loc;
		tile().add(this);
	}

	@Override
	public Sprite sprite(){return sprite;}

	public void tick(){
	}

	public Tile tile(){return loc.tile();}

	@Override
	public String toString(){
		return name;
	}

	public int x(){return loc.x;}

	public int y(){return loc.y;}
}