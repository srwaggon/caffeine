package caffeine.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import caffeine.view.GFX;
import caffeine.world.Location;
import caffeine.world.Tile;


/**
 * base class representing objects in the world.  Entities have a location
 * @author srwaggon
 *
 */
public class Entity{
  protected static int numCharacters = 0;
  protected int id = 0;
  protected int radius = 10;
  protected Location loc;
  private Rectangle frame;
  protected Image sprite;
  protected String name;

  public Entity(){
    this(new Location());
  }

  public Entity(Location l){
    id = numCharacters++;
    loc = l.copy();
    sprite = GFX.getSprite(2);

    name = "" + id;
    tile().add(this);
    System.err.println("Spawning Entity " + name + " at " + loc);
  }

  public Rectangle hitbox(){
    return frame;
  }

  public Location loc(){return loc;}

  public void loc(Location loc){
    tile().remove(this);
    this.loc = loc;
    tile().add(this);
  }

  public void paint(Graphics g){
    int spriteWidth  = sprite.getHeight(null);
    int spriteHeight = sprite.getWidth(null);
    /* Center it, by moving the sprite halfway up and halfway left */
    int renderX = loc.x() - spriteWidth / 2;
    int renderY = loc.y() - spriteHeight / 2;
    g.drawImage(sprite, renderX, renderY, null);
  }

  public int radius(){return radius;}

  public Image sprite(){return sprite;}

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