package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;


public class Tile {

  protected boolean npcpass = true;
  protected boolean pcpass = true;
  protected int damage = 0;
  protected int baseSprite;
  protected int maskSprite;

  public Tile(int sprite) {
    if (sprite == 32){
      pcpass = false;
      maskSprite = sprite;
    } else {
      baseSprite = sprite;
    }
  }

  public boolean canPass() {
    return pcpass;
  }

  public void setPass(boolean b) {
    pcpass = b;
  }

  public boolean isSafe() {
    return damage <= 0;
  }

  public int getSprite() {
    return baseSprite;
  }

  public void setSprite(int id) {
    baseSprite = id;
  }

  @Override
  public String toString() {
    return ".";
  }

  public void tick() {
  }

  public void render(Screen screen, int x, int y, int mapBackgroundSprite) {
    if (baseSprite == 0){
      baseSprite = mapBackgroundSprite;
    }
    screen.render(baseSprite, x, y);
    screen.render(maskSprite, x, y);
  }

  public void onEnter(Entity entity) {

  }
  
  public boolean interact(Entity entity, Item item, Dir dir) {
	  return false;
  }
}