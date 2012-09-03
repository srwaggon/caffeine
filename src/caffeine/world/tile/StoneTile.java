package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.items.Item;
import caffeine.world.Dir;

public class StoneTile extends Tile {

  public StoneTile() {
    this.baseSprite = 4;
    this.maskSprite = 32;
    pcPass = false;
    npcPass = false;
  }

  
  public boolean interact(Entity entity, Item item, Dir dir) {
    
    return true;
  }
}
