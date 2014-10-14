package caffeine.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Entity implements Serializable, Collideable {
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
  protected double dx = 0;
  protected double dy = 0;
  protected double dz = 0;
  protected double za = 0;
  protected double speed = 50;
  protected int width = 8; // (left to right)
  protected int length = 8; // (front to back)
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
        && intersects(e.getX() - e.getLength(), e.getY() - e.getWidth(), e.getX()
            + e.width, e.getY() + e.length);
  }

  public boolean intersects(double left, double top, double right, double bottom) {
    return !(x + getLength() < left || y + getWidth() < top || x - getLength() > right || y
        - getWidth() > bottom);
  }

  public boolean isRemoved() {
    return removed;
  }

  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC();
  }

  public void remove() {
    removed = true;
  }

  public void render(Screen screen) {
    screen.render(sprite, (int) x - Map.tileSize/2, (int) y
        - Map.tileSize/2 - (int) z);
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

  public void tick(double ticksPerSecond) {
    if (shouldRemove()) {
      remove();
    }
    applyMotion(ticksPerSecond);
  }

  protected double ticksPerSecond;
  private void applyMotion(double ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
    applyGravity(ticksPerSecond);
    applySpeed(ticksPerSecond);
    resetSpeed();
  }

  double gravity = 5.0;
  private void applyGravity(double ticksPerSecond) {
    dz += za;
    z += dz;
    
    if (z < 0) {
      za = 0;
      dz = 0;
      z = 0;
    }
    if (za >= 0) {
      za -= gravity / ticksPerSecond;
    }
  }

  protected void resetSpeed() {
    dx = 0.0;
    dy = 0.0;
  }

  protected void applySpeed(double ticksPerSecond) {
    double projectedX = x + dx;
    double projectedY = y + dy;

    final double left = projectedX - getWidth();
    final double top = projectedY - getLength();
    final double right = projectedX + getWidth();
    final double bottom = projectedY + getLength();
    
    List<Tile> collidingTiles = map.getTiles(left, top, right, bottom);
    List<Entity> collidingEntities = map.getEntities(left, top, right, bottom);
    List<Collideable> collideables = new ArrayList<Collideable>(
        collidingTiles.size() + collidingEntities.size());
    collideables.addAll(collidingTiles);
    collideables.addAll(collidingEntities);
    
    
    
    for (Tile tile : collidingTiles) {
      if (tile.blocksNPC()) {
//        System.out.println("BUMP");
//        tile.setSprite(32);
        return;
      }
    }
    
//    System.out.printf("(%.1f, %.1f, %.1f, %.1f)\n", left, top, right, bottom);

    x += dx;
    y += dy;
  }

  public void north() {
    dy = -speed / ticksPerSecond;
    sprite = 126;
  }

  public void east() {
    dx = speed / ticksPerSecond;
    sprite = 127;
  }

  public void south() {
    dy = speed / ticksPerSecond;
    sprite = 128;
  }

  public void west() {
    dx = -speed / ticksPerSecond;
    sprite = 129;
  }

  private boolean shouldRemove() {
    return false;
  }

  public int getWidth() {
    return isSideways() ? length : width;
  }

  public int getLength() {
    return isSideways() ? width : length;
  }

  protected boolean isSideways() {
    return dir == Dir.E || dir == Dir.W;
  }

  @Override
  public String toString() {
    return "# " + ID + " X " + x + " Y " + y + " Z " + z;
  }

  public boolean touchedBy(Entity entity) {
    return true;
  }

  @Override
  public boolean onCollide() {
    return false;
  }

  @Override
  public boolean collides(double left, double top, double right, double bottom) {
    return false;
  }

}
