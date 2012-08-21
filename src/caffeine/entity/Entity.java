package caffeine.entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
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
  protected boolean removed = false;

  protected int xr = 2;
  protected int yr = 2;
  public int spriteID = 67;
  protected int speed = 1;

  /* object fields */
  public LinkedList<Action> actionPlans = new LinkedList<Action>();

  protected Dir dir = Dir.DOWN;
  protected Loc loc;
  protected String name = "Entity[" + id + "]";
  protected World world;

  /* CONSTRUCTORS */
  public Entity(World world) {
    this.world = world;
    loc = world.getDefaultSpawn();
    world.getMap(loc.mapID).addEntity(this, loc.x, loc.y);
  }

  public void tick() {
  }

  public boolean move(int xa, int ya) {
    Map map = getMap();

    int nx = loc.x + xa; // next x
    int ny = loc.y + ya; // next y

    List<Tile> nextTiles = map.getTiles(nx - xr, ny - yr, nx + xr, ny + yr);
    for (Tile t : nextTiles) {
      if (!isValidTile(t)) {
        return false;
      }
    }

    // Check collision with each entity.
    Collection<Entity> entities = map.entities();
    for (Entity entity : entities) {

      // if they currently intersect, move freely.
      if (entity.equals(this) || intersects(entity)) {
        continue;
      }

      // If they're going to intersect, inform them.
      if (entity.intersects(nx - xr, ny - yr, nx + xr, ny + yr)) {
        if (!entity.touchedBy(this)) {
          return false;
        }
      }
    }

    // Change location.
    loc.x += xa;
    loc.y += ya;

    return true;
  }

  public boolean push(Entity pushee, int xa, int ya) {
    pushee.actionPlans.clear();
    return pushee.move(xa, ya);
  }

  public final void render(Screen screen) {
    screen.render(spriteID, loc.x - Map.tileSize / 2, loc.y - Map.tileSize / 2
        - loc.z);
  }

  public boolean intersects(int x0, int y0, int x1, int y1) {
    return !(loc.x + xr < x0 || loc.y + yr < y0 || loc.x - xr > x1 || loc.y
        - yr > y1);
  }

  public boolean intersects(Entity e) {
    return !equals(e)
        && intersects(e.loc.x - e.xr, e.loc.y - e.yr, e.loc.x + e.xr, e.loc.y
            + e.yr);
  }

  public boolean touchedBy(Entity entity) {
    return true;
  }

  public void takeDamage(int dmg) {
  }

  public void takeItem(ItemEntity item) {
  }

  /* ACCESSORS */

  public Dir getDirection() {
    return dir;
  }

  public void setDirection(Dir dir) {
    this.dir = dir;
  }

  public static int getPopulation() {
    return Entity.numEntities;
  }

  public int getID() {
    return id;
  }

  public Loc getLoc() {
    return loc;
  }

  public void setLoc(Loc loc) {
    this.loc = loc;
  }

  public Map getMap() {
    return world.getMap(loc.mapID);
  }

  public int getSpeed() {
    return speed;
  }

  public World getWorld() {
    return world;
  }

  public boolean isValidTile(Tile tile) {
    return tile.canPass();
  }

  public boolean isRemoved() {
    return removed;
  }

  public void remove() {
    removed = true;
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
}
