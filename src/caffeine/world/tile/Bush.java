package caffeine.world.tile;

import pixl.Screen;
import caffeine.entity.Entity;
import caffeine.items.Heart;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.world.Dir;

public class Bush extends TileObject {
  
  long time = System.currentTimeMillis();
  Stage stage;
  
  enum Stage {
    SEEDS(6), SAPLING(7), LOW(8), HIGH(9);
    public final int SPRITE;
    
    private Stage(int sprite) {
      SPRITE = sprite;
    }
  }
  
  public Bush() {
    super(TileType.GRASS, '#', 0, true, true, true);
    stage = Stage.values()[((int) Math.random() * 4)];
  }
  
  @Override
  public boolean interact(Entity entity, Item item, Dir dir) {
    if (item.getType() == ItemType.tool || item.getType() == ItemType.weapon) {
      remove();
    }
    return false;
  }
  
  @Override
  public void dropItem() {
  }
  
  @Override
  public Item itemDropped() {
    return new Heart(0);
  }
  
  @Override
  public void render(Screen screen, Tile tile, int x, int y) {
    screen.render(stage.SPRITE, x, y);
  }
  
  @Override
  public void tick() {
    if (stage != Stage.HIGH) {
      long now = System.currentTimeMillis();
      if (now - time > 30 * 1000) {
        time = now;
        stage = Stage.values()[stage.ordinal() + 1];
      }
    }
  }
  
}
