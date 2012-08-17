package caffeine.entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
import caffeine.entity.brain.Brain;
import caffeine.gfx.Screen;
import caffeine.world.Dir;
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
  protected int xr = 8;
  protected int yr = 8;
  public int spriteID = 3;
  protected int speed = 2;

  /* object fields */
  public LinkedList<Action> actionPlans = new LinkedList<Action>();
  protected Brain brain;
  protected Dir dir = Dir.DOWN;
  protected Loc loc;
  protected String name = "Entity[" + id + "]";
  protected World world;

  /* CONSTRUCTORS */
  public Entity(World world) {
    this.world = world;
    loc = world.getDefaultSpawn();
    world.getMap(loc.mapID).addEntity(this);
    brain = new Brain(this);
  }

  /**
   * Enact the regularly scheduled routines this entity must perform.
   */
  public void tick() {
    if (isAlive) {
      if (isMoving) {
        int xa = dir.dx() * speed;
        int ya = dir.dy() * speed;
        move(xa, ya, true);
      }
      if (brain != null)
        brain.tick();
    }
  }

  public boolean move(int xa, int ya) {
    loc.x += xa;
    loc.y += ya;
    return true;
  }

  public boolean move(int xa, int ya, boolean b) {
    Map map = world.getMap(loc.mapID);

    int nx = loc.x + xa; // next x
    int ny = loc.y + ya; // next y

    List<Tile> nextTiles = map.getTiles(nx - xr, ny - yr, nx + xr, ny + yr);
    for (Tile t : nextTiles)
      if (!isValidTile(t) || !t.canPass())
        return false;

    // Check collision with each entity.
    Collection<Entity> potentialColliders = map.entities();
    for (Entity collider : potentialColliders) {

      // if they currently intersect, move freely.
      if (collider.equals(this) || intersects(collider))
        continue;

      // If they're going to intersect, inform them.
      if (collider.intersects(nx - xr, ny - yr, nx + xr, ny + yr))
        // If the collision is bad, the move is unsuccessful.

        return false;
      //return push(collider, xa, ya);
    }

    // Change location.
    loc.x += xa;
    loc.y += ya;

    isMoving = false;
    return true;
  }

  public boolean push(Entity pushee, int xa, int ya) {
    pushee.actionPlans.clear();
    return pushee.move(xa, ya, true);
  }

  /**
   * Takes a graphics object and draws the representation of this entity.
   * 
   * @param g
   *          Graphics used to draw this entity
   */
  public final void render(Screen screen) {
    screen.render(spriteID, loc.x - Map.tileSize / 2, loc.y - Map.tileSize / 2);
  }

  public boolean intersects(int x0, int y0, int x1, int y1) {
    return !(loc.x + xr < x0 || loc.y + yr < y0 || loc.x - xr > x1 || loc.y - yr > y1);
  }

  /**
   * 
   * @param Entity
   *          of which possible collision
   * @return true if collision detected
   */
  public boolean intersects(Entity e) {
    return !equals(e) && intersects(e.loc.x - e.xr, e.loc.y - e.yr, e.loc.x + e.xr, e.loc.y + e.yr);
  }

  public boolean handleCollision(Entity collidingEntity) {
    return true;
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

  public Dir getDirection() {
    return dir;
  }

  public void setDirection(Dir dir) {
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

  public int getSpeed() {
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