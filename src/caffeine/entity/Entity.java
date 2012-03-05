package caffeine.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import caffeine.view.Animation;
import caffeine.world.Location;
import caffeine.world.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity {
  protected static int numEntities = 0;
  protected int id = 0;
  protected int size = 24;
  protected Location loc;
  protected Animation anim;
  protected String name;
  
  public Entity() {
    this(new Location());
  }
  
  public Entity(Location l) {
    id = Entity.numEntities++; // This must stay first.
    name = "" + id;
    loc = l;
    loc.tile().add(this);
    
    int[] walkSprites = { 3, 4 };
    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
    
    // System.out.println("Spawning Entity " + this);
  }
  
  public static int count() {
    return Entity.numEntities;
  }
  
  public int getID() {
    return id;
  }
  
  public Location loc() {
    return loc;
  }
  
  public void loc(Location loc) {
    this.loc = loc;
  }
  
  public int radius() {
    return size;
  }
  
  public Tile tile() {
    return loc.tile();
  }
  
  public ArrayList<Location> vertices() {
    ArrayList<Location> vertices = new ArrayList<Location>();
    int mapID = loc.mapID();
    int x = loc.x();
    int y = loc.y();
    int ts = loc.map().tileSize();
    int padding = ts - size;
    
    // topleft, topright, bottomleft, bottomright
    vertices.add(new Location(mapID, x + padding, y + padding));
    vertices.add(new Location(mapID, x + padding, y + ts - padding - 1));
    vertices.add(new Location(mapID, x + ts - padding - 1, y + padding));
    vertices
        .add(new Location(mapID, x + ts - padding - 1, y + ts - padding - 1));
    return vertices;
  }
  
  public void render(Graphics2D g2) {
    anim.render(g2, loc.x(), loc.y());
    // System.out.println("Rendering " + this);
  }
  
  public String toString() {
    return "entity" + " " + id + " " + loc.toString();
  }
  
  public void finalize() {
    try {
      System.out.println("--");
      Entity.numEntities--;
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
  
  public void tick() {
  }
}