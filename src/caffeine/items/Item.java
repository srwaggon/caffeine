package caffeine.items;

import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;



public abstract class Item {

  public abstract void onTake(Entity entity);

  public abstract int getSprite();

  public abstract ItemType getType();

  public abstract boolean interact(Tile tile, Entity user, Dir dir);

}
