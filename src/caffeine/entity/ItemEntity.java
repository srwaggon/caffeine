package caffeine.entity;

import caffeine.world.World;


public class ItemEntity extends Entity{

  public ItemEntity(World world) {
    super(world);
  }


  public void touchedBy(Entity entity){
    entity.takeItem(this);
  }

  public void take(Entity entity){
    //item.onTake(this);
    remove();
  }
}
