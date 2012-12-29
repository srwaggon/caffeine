package caffeine.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Map;
import caffeine.world.tile.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity implements Serializable {
  private static final long serialVersionUID = 159464396047740407L;

  protected static int numEntities = 0;

  public static int getPopulation() {
    return Entity.numEntities;
  }

  protected boolean removed = false;
  public final String ID;
  protected int mapID = 0;
  protected int speed = 1;
  protected int sprite = 128;
  protected int x = 32;
  protected int y = 32;
  protected int z = 0;
  protected int xr = 3; // width/2
  protected int yr = 3; // height/2
  protected Dir dir = Dir.S;

  protected Map map;

  /* CONSTRUCTORS */
  public Entity() {
    ID = "" + Entity.numEntities++;
  }

  public Entity(String id) {
    ID = id;
    Entity.numEntities++;
  }

  public void addItem(Item item) {
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

  public Dir getDir() {
    return dir;
  }

  public int getHP() {
    return 0;
  }

  public Map getMap() {
    return map;
  }

  public int getMapID() {
    return mapID;
  }

  public int getSpeed() {
    return speed;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public void heal(int n) {
  }

  public boolean intersects(Entity e) {
    return !equals(e)
        && intersects(e.getX() - e.xr, e.getY() - e.yr, e.getX() + e.xr,
            e.getY() + e.yr);
  }

  // top left corner and bottom right corner
  public boolean intersects(int x0, int y0, int x1, int y1) {
    return !(x + xr < x0 || y + yr < y0 || x - xr > x1 || y - yr > y1);
  }

  public boolean isRemoved() {
    return removed;
  }

  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC();
  }

  public void jump() {
  }

  public void knockback(int x, int y) {
  }

  public boolean move(Dir dir) {
    return move(dir.dx * speed, dir.dy * speed);
  }

  public boolean move(int xa, int ya) {
    boolean notStopped = move2(xa, 0);
    return notStopped && move2(0, ya);
  }

  public boolean move2(int xa, int ya) {
    int nx = x + xa; // next x
    int ny = y + ya; // next y

    List<Tile> nextTiles = map.getTiles(nx - xr, ny - yr, nx + xr, ny + yr);
    for (Tile t : nextTiles) {
      if (!isValidTile(t)) {
        return false;
      }
    }

    // Check collision with each entity.
    Collection<Entity> entities = map.getEntities();
    for (Entity entity : entities) {

      // Ignore collisions with self.
      if (entity.equals(this)) {
        continue;
      }

      // if they currently intersect, move freely.
      if (intersects(entity) && (z > 0 == entity.z > 0)) {
        continue;
        //entity.touchedBy(this);
      }

      if (entity.intersects(nx - xr, ny - yr, nx + xr, ny + yr) && (z > 0 == entity.z > 0)) {
        // If they're going to intersect, inform them.
        if (!entity.touchedBy(this)) {
          return false;
        }
      }
    }

    // Change location.
    x += xa;
    y += ya;


    for (Tile t : nextTiles) {
      t.onEnter(this);
    }

    return true;
  }

  public boolean push(Entity pushee, int xa, int ya) {
    return pushee.move(xa, ya);
  }

  public void remove() {
    removed = true;
  }

  public void render(Screen screen) {
    screen.render(sprite, x - Map.tileSize / 2, y - Map.tileSize / 2 - z);
  }

  public void setDir(Dir dir) {
    this.dir = dir;
  }

  public void setLoc(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setMap(Map map) {
    this.map = map;
    mapID = map.getID();
  };

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public void setSprite(int sprite) {
    this.sprite = sprite;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setZ(int z) {
    this.z = z;
  }

  public void takeDamage(int dmg) {
  }

  public void takeDamage(int dmg, Dir dir) {
  }

  public void takeItem(ItemEntity item) {
  }

  public void tick() {
  }

  public String toString() {
    return "# " + ID + " X " + x + " Y " + y + " Z " + z;
  }

  public boolean touchedBy(Entity entity) {
    return true;
  }
}
