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
  protected double x = 0;
  protected double y = 0;
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
    double myRight = x + getLength();
    double myBottom = y + getWidth();
    double myLeft = x - getLength();
    double myTop = y - getWidth();
    return !(myRight < left || myBottom < top || myLeft > right || myTop > bottom);
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

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setZ(double z) {
    this.z = z;
  }
  
  public void setLoc(double x, double y, double z) {
    this.x = x;
    this.y = y;
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
    final double right = projectedX + getWidth();
    
    final double top = projectedY - getLength();
    final double bottom = projectedY + getLength();
    
    List<Tile> collidingTiles = map.getTiles(left, top, right, bottom);
    
    List<Entity> collidingEntities = map.getEntities(left, top, right, bottom);
    List<Collideable> collideables = new ArrayList<Collideable>(
        collidingTiles.size() + collidingEntities.size());
    collideables.addAll(collidingTiles);
    collideables.addAll(collidingEntities);
    
//    Tile topLeft = map.getTiles(left, top, left, top).get(0);
    Tile bottomRight = map.getTiles(right, bottom, right, bottom).get(0);
    
//    System.out.printf("tl(%.1f,%.1f) %b\n", left, top, topLeft.blocksNPC());
//    System.out.printf("\tbr(%.1f,%.1f) %b\n", right, bottom, bottomRight.blocksNPC());
    System.out.println(collidingTiles.contains(bottomRight));
    
    for (Tile tile : collidingTiles) {
      if (tile.blocksNPC() && z == 0) {
        return;
      }
    }

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

  public List<Tile> getTiles() {
    return map.getTiles(x - getWidth(), y - getLength(), x + getWidth(), y + getLength());
  }
}
