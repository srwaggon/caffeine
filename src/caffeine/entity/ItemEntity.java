package caffeine.entity;

import caffeine.sfx.Sound;
import caffeine.world.World;


public class ItemEntity extends Entity{

  public ItemEntity(World world) {
    super(world);
  }


  public boolean touchedBy(Entity entity){
    entity.takeItem(this);
    Sound.ITEM.play();
    return true;
  }

  public void take(Entity entity){
    //item.onTake(this);
    remove();
  }
}
