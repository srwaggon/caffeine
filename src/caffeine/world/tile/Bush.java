package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;


public class Bush extends TileObject {

  public Bush() {
    super('m', 128, true, true, true);
  }

  @Override
  public boolean interact(Entity entity, Item item, Dir dir) {
    if (item.getType() == ItemType.tool ||
        item.getType() == ItemType.weapon){
      remove();
    }
    return false;
  }

  @Override
  public void dropItem() {
  }

}
