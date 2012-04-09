package caffeine.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import caffeine.view.Animation;
import caffeine.world.Loc;
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
  protected Loc loc;
  protected Animation anim;
  protected String name;
  
  public Entity() {
    this(new Loc(0, 48, 48));
  }
  
  public Entity(Loc l) {
    id = Entity.numEntities++; // This must stay first.
    name = "" + id;
    loc = l;
    // loc.tile().add(this);
    
    int[] walkSprites = { 3, 4 };
    
    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
  }
  
  public static int count() {
    return Entity.numEntities;
  }
  
  public int getID() {
    return id;
  }
  
  public Loc loc() {
    return loc;
  }
  
  public void loc(Loc loc) {
    this.loc.tile().remove(this);
    this.loc = loc;
    loc.tile().add(this);
  }
  
  public int radius() {
    return size;
  }
  
  public Tile tile() {
    return loc.tile();
  }
  
  public Rectangle hitbox() {
    return new Rectangle(loc.x() - size / 2, loc.y() - size / 2, size, size);
  }
  
  public final void render(Graphics2D g2) {
    g2.draw(hitbox());
    anim.render(g2, loc.x(), loc.y());
  }
  
  public String toString() {
    return "entity" + " " + id + " " + loc.toString();
  }
  
  public void finalize() {
    try {
      Entity.numEntities--;
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
  
  public void tick() {
  }
}