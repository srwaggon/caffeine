package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;


public class Water extends TileObject {

  private static final long serialVersionUID = 3857607660382089726L;

  public Water() {
    super(TileType.WATER, '~', 64, false, false, false);
  }

  @Override
  public boolean interact(Entity entity, Item item, Dir dir) {
    return false;
  }

  @Override
  public void dropItem() {
  }

}
