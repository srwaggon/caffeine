package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;

public class Stone extends TileObject {

  private int hp = 10;

  public Stone() {
    super('D', 32, true, true, true);
  }

  public boolean interact(Entity entity, Item item, Dir dir) {
    if (item.getType() == ItemType.tool) {
      hp--;
    }
    if (hp <= 0) {
      remove();
    }
    return true;
  }

  @Override
  public void dropItem() {
  }
}
