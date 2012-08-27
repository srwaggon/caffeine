package caffeine.items;

import caffeine.entity.Entity;



public class Heart extends Item {
  protected int amount = 10;

  public Heart(int amount) {
    sprite = 96;
    this.amount = amount;
  }

  public void onTake(Entity entity){
    entity.heal(1);
    System.out.println(entity.getHP());
  }

}
