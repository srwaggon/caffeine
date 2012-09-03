package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;


public final class Nothing extends TileObject {

  public Nothing() {
    super(TileType.dirt, ' ', 0, false, false, true);
  }

  public boolean interact(Entity entity, Item item, Dir dir) {
    return true;
  }

  @Override
  public void dropItem() {
  }

}
