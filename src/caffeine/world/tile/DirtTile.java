package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Map;


public class DirtTile extends Tile {

  public DirtTile(Map map, int x, int y) {
    super(map, x, y);
    baseSprite = 4;
  }

  @Override
  public boolean blocksPC() {
    return false;
  }

  @Override
  public boolean blocksNPC() {
    return false;
  }

  @Override
  public boolean isSafe() {
    return true;
  }

  @Override
  public char getSymbol() {
    return '.';
  }

  @Override
  public void onEnter(Entity entity) {
  }

  @Override
  public boolean interact(Entity entity, Item item, Dir dir) {
    return false;
  }

}
