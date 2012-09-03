package caffeine.entity;

import caffeine.items.Item;
import caffeine.world.World;


public class ItemEntity extends Entity{
  long spawnTime = System.currentTimeMillis();
  Item item;

  public ItemEntity(Item item, World world) {
    super(world);
    this.item = item;
    sprite = item.getSprite();
  }


  public boolean touchedBy(Entity entity){
    long now = System.currentTimeMillis();
    if (now - spawnTime > 200){
      entity.takeItem(this);
    }
    return true;
  }

  public void take(Entity entity){
    item.onTake(entity);
    item.playSound();
    remove();
  }
}
