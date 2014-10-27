package caffeine.entity;

import caffeine.items.Item;


public class ItemEntity extends Entity{
  private static final long serialVersionUID = 1183999769472205714L;
  long spawnTime = System.currentTimeMillis();
  int lifetime; // in milliseconds
  Item item;
  boolean flip = false;
  int za = 4;

  public ItemEntity(Item item) {
    this.item = item;
    sprite = item.getSprite();
    lifetime = 5000;
  }

  public void tick(double ticksPerSecond) {
    if (System.currentTimeMillis() - spawnTime > lifetime) {
      remove();
      return;
    }

    flip = !flip;
    if (z >= 0 && flip) {
      z += za--;
      if (z <= 0) {
        z = 0;
        za = 0;
      }
    }
  }

  public void take(Entity entity){
    item.onTake(entity);
    item.playPickupSound();
    remove();
  }

  public boolean touchedBy(Entity entity){
    long now = System.currentTimeMillis();
    if (now - spawnTime > 200){
      entity.takeItem(this);
    }
    return true;
  }

}
