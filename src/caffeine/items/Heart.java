package caffeine.items;

import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;



public class Heart extends Item {
  protected int amount = 10;

  public Heart(int amount) {
    this.amount = amount;
  }

  public void onTake(Entity entity){
    entity.heal(1);
  }

  @Override
  public int getSprite() {
    return 96;
  }

  @Override
  public ItemType getType() {
    return ItemType.consumable;
  }

  @Override
  public boolean interact(Tile tile, Entity user, Dir dir) {
    return tile.interact(user, this, dir);
  }

}
