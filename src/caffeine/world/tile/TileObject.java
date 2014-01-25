package caffeine.world.tile;

import java.io.Serializable;

import pixl.Screen;
import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;

public abstract class TileObject implements Serializable {
  
  private static final long serialVersionUID = 1674197575118814994L;
  
  protected boolean blocksPC = false;
  protected boolean blocksNPC = false;
  protected boolean isSafe = true;
  protected boolean removed = false;
  protected char symbol = ' ';
  public int sprite = 0;
  protected TileType type;
  
  public TileObject() {
  }
  
  public TileObject(TileType type, char symbol, int sprite, boolean blocksPC,
      boolean blocksNPC, boolean isSafe) {
    this.symbol = symbol;
    this.sprite = sprite;
    this.blocksPC = blocksPC;
    this.blocksNPC = blocksNPC;
    this.isSafe = isSafe;
    this.type = type;
  }
  
  public final boolean isRemoved() {
    return removed;
  }
  
  public final boolean isSafe() {
    return isSafe;
  }
  
  public final boolean blocksPC() {
    return blocksPC;
  }
  
  public final boolean blocksNPC() {
    return blocksNPC;
  }
  
  public final int getSprite() {
    return sprite;
  }
  
  public final char getSymbol() {
    return symbol;
  }
  
  public final void remove() {
    removed = true;
  }
  
  public void render(Screen screen, Tile t, int x, int y) {
    screen.render(sprite, x, y);
  }
  
  public abstract boolean interact(Entity entity, Item item, Dir dir);
  
  public abstract void dropItem();
  
  public Item itemDropped() {
    return null;
  }
  
  public void tick() {
  }
  
}
