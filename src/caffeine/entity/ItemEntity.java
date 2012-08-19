package caffeine.entity;

import caffeine.entity.items.Item;
import caffeine.sfx.Sound;
import caffeine.world.World;


public class ItemEntity extends Entity{

  Item item;

  public ItemEntity(World world) {
    super(world);
  }


  public boolean touchedBy(Entity entity){
    entity.takeItem(this);
    return true;
  }

  public void take(Entity entity){
    item.onTake(this);
    Sound.ITEM.play();
    remove();
  }
}
