package caffeine.items.weapons;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;


public class Weapon extends Item {

  @Override
  public void onTake(Entity entity) {
  }

  @Override
  public int getSprite() {
    return 0;
  }

  @Override
  public ItemType getType() {
    return ItemType.weapon;
  }

  @Override
  public boolean interact(Tile tile, Entity user, Dir dir) {
    return tile.interact(user, this, dir);
  }

}
