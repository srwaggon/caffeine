package caffeine.world;

import java.awt.Rectangle;

public class Tile {

  protected boolean pass = true;
  protected int damage = 0;
  protected int spriteID = 0;
  protected int x, y;
  protected static int size = 32;

  protected Map parent;
  protected Rectangle bounds;

  public Tile(int x, int y, int tileSize, Map parent) {
    this.x = x;
    this.y = y;
    bounds = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
    this.parent = parent;
  }

  public Rectangle getBounds() {
    return bounds;
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

  public boolean isSafe() {
    return damage <= 0;
  }

  public int getSpriteID() {
    return spriteID;
  }

  public void setSprite(int id) {
    spriteID = id;
  }

  @Override
  public String toString() {
    return ".";
  }

  public void tick() {
  }
}