package caffeine.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import caffeine.action.Action;
import caffeine.entity.brain.Brain;
import caffeine.view.Animation;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity {
  /* static fields */
  protected static int numEntities = 0;

  /* primitive fields */
  protected int id;
  protected boolean isAlive = true;
  protected int size = 24;
  protected int speed = 1;

  /* object fields */
  protected Animation anim;
  protected Brain brain = new Brain();
  protected Direction dir = Direction.S;
  protected Loc loc;
  protected String name;

  public Entity() {
    this(new Loc(0, 48, 48));
  }

  /* CONSTRUCTORS */
  public Entity(Loc l) {
    id = Entity.numEntities++; // This must stay first.
    name = "" + id;
    loc = l;
    int[] walkSprites = { 3, 4 };

    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
  }

  /* ACCESSORS */
  public boolean alive() {
    return isAlive;
  }

  /**
   * Returns the number of currently existing entities. This number is
   * incremented each time an entity is created and reduced each time an entity
   * is destroyed.
   * 
   * @return The number of entities.
   */
  public static int count() {
    return Entity.numEntities;
  }

  /**
   * Returns a unique numerical digit representing the entity's identification.
   * 
   * @return unique id as digit
   */
  public int getID() {
    return id;
  }

  /**
   * Returns a Rectangle object of which the entity is considered to occupy in
   * space. Objects which fall within the rectangle or overlap with the
   * rectangle are considered to be inside of the entity. This rectangle serves
   * for basic hit detection purposes.
   * 
   * @return rectangle of which this entity occupies
   */
  public Rectangle hitbox() {
    return new Rectangle(loc.x() - size / 2, loc.y() - size / 2, size, size);
  }

  /**
   * Returns the current Location of the entity.
   * 
   * @return location of entity
   */
  public Loc loc() {
    return loc;
  }

  /**
   * 
   * @return speed that this entity can move
   */
  public int speed() {
    return speed;
  }

  /**
   * Takes a tile and determines if the tile is a validly accessible location
   * according to this entity for movement purposes.
   * 
   * @param tile
   * @return boolean representing whether or not this tile is a validly
   *         accessible location.
   */
  public boolean validLoc(Tile tile) {
    return tile.isEmpty();
  }

  /* MUTATORS */
  public void alive(Boolean b) {
    isAlive = b;
  }

  public void brain(Brain b) {
    brain = b;
  }

  public void face(Direction dir) {
    this.dir = dir;
  }

  /**
   * Takes a graphics object and draws the representation of this entity.
   * 
   * @param g
   *          Graphics used to draw this entity
   */
  public final void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.draw(hitbox());
    anim.render(g2, loc.x(), loc.y());
  }

  /**
   * Enact the regularly scheduled routines this entity must perform.
   */
  public void tick(Map map) {
    if (isAlive) {
      for (Action act : brain.next(this, map)) {
        act.performBy(this);
      }
    }
  }

  @Override
  public String toString() {
    return "entity" + " " + id + " " + loc.toString();
  }

  @Override
  public void finalize() {
    try {
      Entity.numEntities--;
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

}