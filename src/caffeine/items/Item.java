package caffeine.items;

import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.tile.Tile;



public class Item {

  int sprite;

  public void onTake(Entity entity) {

  }

  public int getSprite() {
    return sprite;
  }
  
  public boolean interact(Tile tile, Entity user, Dir dir) {
	  return false;
  }

}
