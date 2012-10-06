package caffeine.entity;

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
public class Entity {
  /* static fields */
  protected static int numEntities = 0;
  /* primitive fields */
  protected int id = Entity.numEntities++;
  protected boolean removed = false;

  protected int mapID = 0;
  protected int x = 32;
  protected int y = 32;
  protected int z;
  protected int xr = 2;
  protected int yr = 2;
  protected int sprite = 128;
  protected int speed = 1;

  /* object fields */
  protected Dir dir = Dir.S;
  protected Map map;

  /* CONSTRUCTORS */
  public Entity() {
  }

  public Entity(int id){
    this.id = id;
  }

  public void tick() {
  }

  public boolean move(Dir dir){
    boolean notStopped = move2(dir.dx() * speed, 0);
    return notStopped && move2(0, dir.dy() * speed);
  }

  public boolean move(int xa, int ya){
    boolean notStopped = move2(xa, 0);
    return notStopped && move2(0, ya);
  }

  public boolean move2(int xa, int ya) {
    Map map = getMap();

    if (xa != 0 && ya != 0){
      return false;
    }

    setDir(Dir.whichDir(xa, ya));

    int nx = x + xa; // next x
    int ny = y + ya; // next y

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
      if (entity.equals(this)) {
        continue;
      }

      if (intersects(entity)) {
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
    x += xa;
    y += ya;

    for (Tile t : nextTiles){
      t.onEnter(this);
    }

    return true;
  }

  public void moveTo(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void heal(int n){
  }

  public boolean push(Entity pushee, int xa, int ya) {
    return pushee.move(xa, ya);
  }

  public final void render(Screen screen) {
    screen.render(sprite, x - Map.tileSize / 2, y - Map.tileSize / 2
        - z);
  }

  public boolean intersects(int x0, int y0, int x1, int y1) {
    return !(x + xr < x0 || y + yr < y0 || x - xr > x1 || y
        - yr > y1);
  }

  public boolean intersects(Entity e) {
    return !equals(e)
        && intersects(e.getX() - e.xr, e.getY() - e.yr, e.getX() + e.xr, e.getY()
            + e.yr);
  }

  public void knockback(int x, int y){ }

  public boolean touchedBy(Entity entity) {
    return true;
  }

  public void takeDamage(int dmg) { }

  public void takeDamage(int dmg, Dir dir){ }

  public void takeItem(ItemEntity item) { }

  public static int getPopulation() {
    return Entity.numEntities;
  }

  
  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC();
  }

  
  /* ACCESSORS */
  
  public Dir getDirection() { return dir; }
  
  public int getHP() { return 0; }
  
  public int getID() { return id; }
  
  public Map getMap() { return map; }
  
  public int getMapID() { return mapID; }
  
  public int getSpeed() { return speed; }
  
  public int getX() { return x; }
  
  public int getY() { return y; }
  
  public int getZ() { return z; }
  
  public boolean isRemoved() { return removed; }

  
  /* MUTATORS */
  
  public void setDir(Dir dir) { this.dir = dir; }
  
  public void setMap(Map map) { this.map = map; mapID = map.getID(); }
  
  public void setMapID(int mapID) { this.mapID = mapID; }
  
  public void setSprite(int sprite) { this.sprite = sprite; }
  
  public void setX(int x) { this.x = x; }
  
  public void setY(int y) { this.y = y; }
  
  public void setZ(int z) { this.z = z; }

  public void remove() { removed = true; }

  
  
  
  /* UTILITIES */
  
  
  @Override
  public String toString() {
    return "# " + id +
        " X " + x + 
        " Y " + y + "\n";
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

  public void addItem(Item item) {
  }
}
