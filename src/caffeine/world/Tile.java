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
  protected static int size = 32;
  protected Rectangle rect;

  public Tile(int x, int y) {
    rect = new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size);
  }

  public void add(Entity e) {
    occupants.put(e.getID(), e);
  }

  public Rectangle frame() {
    return rect;
  }

  public boolean isEmpty() {
    return occupants.isEmpty();
  }

  public Collection<Entity> occupants() {
    return occupants.values();
  }

  public boolean pass() {
    return pass;
  }

  public void pass(boolean b) {
    pass = b;
  }

  public void remove(Entity e) {
    occupants.remove(e.getID());
  }

  public boolean safe() {
    return damage <= 0;
  }

  public static int size() {
    return Tile.size;
  }

  public static void size(int setTo) {
    if (setTo > 0) {
      Tile.size = setTo;
    }
  }

  public int spriteID() {
    return spriteID;
  }

  public void spriteID(int id) {
    spriteID = id;
  }

  @Override
  public String toString() {
    return ".";
  }

  public void tick() {
  }
}