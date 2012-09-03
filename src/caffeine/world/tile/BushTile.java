package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;
import caffeine.world.Map;


public class BushTile extends Tile {

  public BushTile(Map map, int x, int y) {
    super(map, x, y);
    baseSprite = 34;
    maskSprite = 128;
  }

  @Override
  public boolean blocksPC() {
    return true;
  }

  @Override
  public boolean blocksNPC() {
    return true;
  }

  @Override
  public boolean isSafe() {
    return true;
  }

  @Override
  public char getSymbol() {
    return '#';
  }

  @Override
  public void onEnter(Entity entity) {

  }

  @Override
  public boolean interact(Entity entity, Item item, Dir dir) {
    if (item.getType() == ItemType.tool ||
        item.getType() == ItemType.weapon){
      map.setTile(x, y, new GrassTile(map, x, y));
    }
    return false;
  }

}
