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
  protected Brain brain;
  protected Direction dir = Direction.SOUTH;
  protected Loc loc;
  protected String name = "Entity[" + id + "]";
  protected World world;

  /* CONSTRUCTORS */
  public Entity(World world) {
    this.world = world;
    loc = world.getDefaultSpawn();
    world.getMap(loc.mapID).addEntity(this);

    int[] walkSprites = { 3, 4 };

    Animation walkAnim = new Animation(walkSprites, 200, true);
    anim = walkAnim;
  }

  /**
   * Enact the regularly scheduled routines this entity must perform.
   */
  public void tick() {
    if (isAlive) {
      if (brain != null) {
        actionPlans.addAll(brain.next());
      }
      if (isMoving){
        double xa = dir.dx() * speed;
        double ya = dir.dy() * speed;
        move(xa, ya, true);
      }
      while (!actionPlans.isEmpty()) {
        actionPlans.poll().performBy(this);
      }
    }
  }

  public void move(double xa, double ya){
    loc.x += xa;
    loc.y += ya;
  }

  public boolean move(double xa, double ya, boolean b){
    // project entity's hitbox
    Map map = world.getMap(loc.mapID);

    Rectangle hitboxNext = getHitbox();
    hitboxNext.translate((int) xa, (int) ya);

    List<Tile> nextTiles = map.getTiles(hitboxNext);
    for (Tile t : nextTiles) {
      if (!isValidTile(t)) {
        return false;
      }
    }

    // Check collision with each entity.
    Collection<Entity> potentialColliders = map.entities();
    Rectangle hitbox = getHitbox();
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
    loc.x += xa;
    loc.y += ya;

    isMoving = false;
    return true;
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

  public boolean intersects(Rectangle r){
    return getHitbox().intersects(r);
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

  /* ACCESSORS */
  public boolean isAlive() {
    return isAlive;
  }

  public void die() {
    isAlive = false;
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

  public Direction getDirection() {
    return dir;
  }

  public void setDirection(Direction dir) {
    this.dir = dir;
  }

  public void isMoving(boolean b) {
    isMoving = b;
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

  /**
   * Returns the current Location of the entity.
   * 
   * @return location of entity
   */
  public Loc getLoc() {
    return loc;
  }

  public void setLoc(Loc loc) {
    this.loc = loc;
  }

  public double getSpeed() {
    return speed;
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
  public boolean isValidTile(Tile tile) {
    return tile.canPass();
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

  public boolean isMoving() {
    return isMoving;
  }
}