package caffeine.items;

import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;



public class Item {
  protected int sprite;
  protected ItemType type;

  public void onTake(Entity entity) {

  }

  public int getSprite() {
    return sprite;
  }

  public ItemType getType() {
    return type;
  }

  public boolean interact(Tile tile, Entity user, Dir dir) {
    return false;
  }

}
