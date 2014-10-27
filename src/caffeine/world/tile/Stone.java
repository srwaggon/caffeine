package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;

public class Stone extends TileObject {

  private static final long serialVersionUID = -6751959527517861520L;
  
  private int hp = 10;

  public Stone() {
    super(TileType.DIRT, 'D', 4, true, true, true);
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
