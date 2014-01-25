package caffeine.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pixl.Screen;
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
  protected int sprite = 128;
  protected double x = 32;
  protected double y = 32;
  protected double z = 0;
  protected int width = 3; // (left to right) / 2
  protected int length = 3; // (front to back) / 2
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
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public double getZ() {
    return z;
  }
  
  public void heal(int n) {
  }
  
  public boolean intersects(Entity e) {
    return !equals(e)
        && intersects(e.getX() - e.width, e.getY() - e.length, e.getX()
            + e.width, e.getY() + e.length);
  }
  
  // top left corner and bottom right corner
  public boolean intersects(double left, double top, double right, double bottom) {
    return !(x + width < left || y + length < top || x - width > right || y - length > bottom);
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
  
  public boolean move(double dx, double dy) {
    double nx = x + dx; // next x
    double ny = y + dy; // next y
    
    List<Tile> nextTiles = map.getTiles(nx - width, ny - length, nx + width, ny
        + length);
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
        // entity.touchedBy(this);
      }
      
      if (entity.intersects(nx - width, ny - length, nx + width, ny + length)
          && (z > 0 == entity.z > 0)) {
        // If they're going to intersect, inform them.
        if (!entity.touchedBy(this)) {
          return false;
        }
      }
    }
    
    // Change location.
    x += dx;
    y += dy;
    
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
    screen.render(sprite, ((int) x) - Map.tileSize / 2, ((int) y)
        - Map.tileSize / 2 - ((int) z));
  }
  
  public void setDir(Dir dir) {
    this.dir = dir;
  }
  
  public void setLoc(double x, double y) {
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
  
  @Override
  public String toString() {
    return "# " + ID + " X " + x + " Y " + y + " Z " + z;
  }
  
  public boolean touchedBy(Entity entity) {
    return true;
  }
}
