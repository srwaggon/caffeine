package caffeine.world;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashMap;

import caffeine.entity.Entity;

public class Tile {
  protected HashMap<Integer, Entity> occupants = new HashMap<Integer, Entity>();
  protected boolean pass = true;
  protected int damage = 0;
  protected int spriteID = 0;
  protected Coord coord;
  protected static int size = 32;
  
  protected Map parent;
  protected Rectangle bounds;
  
  public Tile(int x, int y, int tileSize, Map parent) {
    coord = new Coord(x, y);
    bounds = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
    this.parent = parent;
  }
  
  public void addEntity(Entity e) {
    occupants.put(e.getID(), e);
  }
  
  public Rectangle getBounds() {
    return bounds;
  }
  
  public boolean isEmpty() {
    return occupants.isEmpty();
  }
  
  public Collection<Entity> occupants() {
    return occupants.values();
  }
  
  public boolean canPass() {
    return pass;
  }
  
  public Map getMap() {
    return parent;
  }
  
  public void setPass(boolean b) {
    pass = b;
  }
  
  public void removeEntity(Entity e) {
    occupants.remove(e.getID());
  }
  
  public boolean isSafe() {
    return damage <= 0;
  }
  
  public int getSpriteID() {
    return spriteID;
  }
  
  public void setSpriteID(int id) {
    spriteID = id;
  }
  
  @Override
  public String toString() {
    return ".";
  }
  
  public void tick() {
  }
  
  public Coord getCoord() {
    return coord;
  }
}