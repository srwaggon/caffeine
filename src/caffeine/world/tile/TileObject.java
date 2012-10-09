package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;



public abstract class TileObject {

  protected boolean blocksPC;
  protected boolean blocksNPC;
  protected boolean isSafe;
  protected boolean removed;
  protected char symbol;
  public int sprite;
  protected TileType type;

  public TileObject(TileType type, char symbol, int sprite, boolean blocksPC, boolean blocksNPC, boolean isSafe){
    this.symbol = symbol;
    this.sprite = sprite;
    this.blocksPC = blocksPC;
    this.blocksNPC = blocksNPC;
    this.isSafe = isSafe;
    this.type = type;
  }

  public final boolean isRemoved() { return removed; }

  public final boolean isSafe() { return isSafe; }

  public final boolean blocksPC() { return blocksPC; }

  public final boolean blocksNPC() { return blocksNPC; }

  public final int getSprite() { return sprite; }

  public final char getSymbol() { return symbol; }

  public final void remove() { removed = true; }

  public abstract boolean interact(Entity entity, Item item, Dir dir);

  public abstract void dropItem();

  public boolean itemDropped() {
    return false;
  }

}
