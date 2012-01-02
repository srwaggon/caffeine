package caffeine.entity;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

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
  protected int radius = 15;
  protected Location loc;
  private Rectangle frame;
  protected Sprite sprite;
  protected String name;

  public Entity(){
    this(new Location());
  }

  public Entity(Location l){
    id = numCharacters++;
    loc = l.copy();
    sprite = new Sprite(loc, radius);

    name = "" + id;
    tile().add(this);
    System.err.println("Spawning Entity " + name + " at " + loc);
  }

  public void color(Color c){sprite.color(c);}

  public Rectangle hitbox(){
    return frame;
  }

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

  public ArrayList<Location> vertices(){
    ArrayList<Location> vertices = new ArrayList<Location>();
    int mapID = loc.mapID();
    int x = loc.x();
    int y = loc.y();

    // topleft, topright, bottomleft, bottomright
    vertices.add(new Location(mapID, x - radius, y - radius));
    vertices.add(new Location(mapID, x - radius, y + radius));
    vertices.add(new Location(mapID, x + radius, y - radius));
    vertices.add(new Location(mapID, x + radius, y + radius));
    return vertices;
  }
  @Override
  public String toString(){
    return name;
  }
}