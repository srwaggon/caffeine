package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;


public class Tile {

  protected boolean npcPass = true;
  protected boolean pcPass = true;
  protected int damage = 0;
  protected int baseSprite = 34;
  protected int maskSprite = 0;

  public Tile() { }
  
  public Tile(int sprite) {
    if (sprite == 32){
      pcPass = false;
      maskSprite = sprite;
    } else {
      baseSprite = sprite;
    }
  }

  public boolean canPass() {
    return pcPass;
  }

  public void setPass(boolean b) {
    pcPass = b;
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
  
  public static Tile read(int n) {
    Tile tile = null;
    if (n == 32) tile = new StoneTile();
    if (tile == null) tile = new Tile(n);
    return tile;
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