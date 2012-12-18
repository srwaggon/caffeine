package caffeine.items;

import java.io.Serializable;

import caffeine.entity.Entity;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;



public abstract class Item implements Serializable {

  private static final long serialVersionUID = 1721603355299931585L;

  public abstract void onTake(Entity entity);

  public abstract int getSprite();

  public abstract ItemType getType();

  public abstract boolean interact(Tile tile, Entity user, Dir dir);

  public void playPickupSound() {
    Sound.ITEM.play();
  }

  public void playUseSound() {
    Sound.POWERUP.play();
  }

}
