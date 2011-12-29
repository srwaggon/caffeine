package caffeine.entity;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

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
	protected int radius = 16;
	protected Location loc;
	protected Sprite sprite;
	protected String name;

	public Entity(){
		this(new Location());
	}

	public Entity(Location location){
		id = numCharacters++;
		loc = location.copy();
		int x = loc.x - radius;
		int y = loc.y - radius;
		int w = radius*2;
		int h = radius*2;
		sprite = new Sprite(Util.randomColor(), loc,
				new Ellipse2D.Double(
						x, y, w, h));
		name = "" + id;
		System.err.println("Spawning Entity " + name + " at " + loc);
	}

	public void color(Color c){sprite.color(c);}


	public Location loc(){return loc;}

	public void loc(Location loc){
		tile().remove(this);
		this.loc = loc;
		tile().add(this);
	}

	public int radius(){return radius;}

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