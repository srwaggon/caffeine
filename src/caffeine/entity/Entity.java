package caffeine.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
import caffeine.action.Push;
import caffeine.entity.brain.Brain;
import caffeine.view.Animation;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.Tile;
import caffeine.world.World;

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
  protected int id = Entity.numEntities++;
  protected boolean isMoving = false;
  protected boolean isAlive = true;
  protected int size = 24;
  protected double speed = 2.0;


  /* object fields */
  public LinkedList<Action> actionPlans = new LinkedList<Action>();
  protected Animation anim;
  protected Brain brain = null;
  protected Direction dir = Direction.SOUTH;
  protected Loc loc;
  protected String name = "Entity[" + id + "]";
  protected World world;

  /* CONSTRUCTORS */
  public Entity(World world, Loc loc) {
    this.world = world;
    this.loc = loc;
    world.getMap(loc.mapID).addEntity(this);

    int[] walkSprites = { 3, 4 };

    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
  }

  public Entity(World world) {
    this(world, world.getDefaultSpawn());
  }

  /* ACCESSORS */
  public boolean isAlive() {
    return isAlive;
  }

  public Brain getBrain() {
    return brain;
  }

  /**
   * Returns the number of currently existing entities. This number is
   * incremented each time an entity is created and reduced each time an entity
   * is destroyed.
   * 
   * @return The number of entities.
   */
  public static int getPopulation() {
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
  public Rectangle getHitbox() {
    return new Rectangle(loc.x - size / 2, loc.y - size / 2, size, size);
  }

  public boolean intersects(Rectangle r){
    return getHitbox().intersects(r);
  }

  /**
   * Returns the current Location of the entity.
   * 
   * @return location of entity
   */
  public Loc getLoc() {
    return loc;
  }

  public World getWorld() {
    return world;
  }

  /**
   * Takes a tile and determines if the tile is a validly accessible location
   * according to this entity for movement purposes.
   * 
   * @param tile
   * @return boolean representing whether or not this tile is a validly
   *         accessible location.
   */
  public boolean isValidLoc(Tile tile) {
    return tile.canPass();
  }

  public void die() {
    isAlive = false;
  }

  public void move(double xa, double ya){
    loc.x += xa;
    loc.y += ya;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

  /**
   * 
   * @param Entity
   *          of which possible collision
   * @return true if collision detected
   */
  public boolean collidesWith(Entity collider) {
    return !equals(collider) && getHitbox().intersects(collider.getHitbox());
  }

  public boolean handleCollision(Entity collidingEntity) {
    return new Push(collidingEntity).performBy(this);
  }

  /**
   * Takes a graphics object and draws the representation of this entity.
   * 
   * @param g
   *          Graphics used to draw this entity
   */
  public final void render(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    anim.render(g2, loc.x, loc.y);
    g2.draw(getHitbox());
  }

  public void setLoc(Loc loc) {
    this.loc = loc;
  }

  /**
   * Enact the regularly scheduled routines this entity must perform.
   */
  public void tick(Map map) {
    if (isAlive) {
      if (brain != null) {
        actionPlans.addAll(brain.next());
      }
      if (isMoving){
        updateLoc();
      }
      while (!actionPlans.isEmpty()) {
        actionPlans.poll().performBy(this);
      }
    }
  }

  public boolean updateLoc(){
    // project entity's location & hitbox
    double dx = dir.dx() * speed;
    double dy = dir.dy() * speed;
    Loc end = loc.copy().translate(dx, dy);

    Rectangle hitbox = getHitbox();
    Rectangle hitboxNext = getHitbox();
    hitboxNext.translate((int) dx, (int) dy);

    Map map = world.getMap(loc.mapID);
    List<Tile> nextTiles = map.getTiles(hitboxNext);
    Collection<Entity> potentialColliders = map.entities();

    for (Tile t : nextTiles) {
      if (!t.canPass()) {
        return false;
      }
    }

    // Check collision with each entity.
    for (Entity collider : potentialColliders) {
      Rectangle colliderBox = collider.getHitbox();

      // if they currently intersect, move freely.
      if (collider.equals(this) || hitbox.intersects(colliderBox)) {
        continue;
      }

      // If they're going to intersect, inform them.
      if (!collider.equals(this) && hitboxNext.intersects(colliderBox)) {
        // If the collision is bad, the move is unsuccessful.
        return handleCollision(collider);
      }
    }

    // Change location.
    move(dx, dy);

    isMoving = false;
    return true;
  }
  @Override
  public String toString() {
    return name + " @ " + loc.toString();
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

  public void setDirection(Direction dir) {
    this.dir = dir;
  }

  public void isMoving(boolean b) {
    isMoving = b;
  }

  public Direction getDirection() {
    return dir;
  }
}