package caffeine.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import caffeine.action.Action;
import caffeine.action.Motion;
import caffeine.action.instance.StaticMotion;
import caffeine.entity.brain.Brain;
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
  protected int id;
  protected int size = 24;
  protected Loc loc;
  protected Animation anim;
  protected String name;
  public int attack = 1;
  public int health = 10;
  protected boolean isAlive = true;
  protected Brain brain = new Brain();
  protected Motion motion;
  
  public Entity() {
    this(new Loc(0, 48, 48));
    motion = new StaticMotion(getID(), loc);
  }
  
  public Entity(Loc l) {
    id = Entity.numEntities++; // This must stay first.
    name = "" + id;
    loc = l;
    motion = new StaticMotion(getID(), loc);
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
  
  public boolean alive() {
    return isAlive;
  }
  
  public void alive(Boolean b) {
    isAlive = b;
  }
  
  public void brain(Brain b) {
    brain = b;
  }
  
  public void tick() {
    if (isAlive) {
      for (Action act : brain.next(this)) {
        act.performBy(this);
      }
    }
  }
  
  public Motion motion() {
    return motion;
  }
  
  public int size() {
    return size;
  }
}