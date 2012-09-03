package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Map;


public abstract class Tile {

  protected int baseSprite;
  protected int maskSprite;
  protected int x, y;

  protected Map map;


  public Tile(Map map, int x, int y) {
    this.map = map;
    this.x = x;
    this.y = y;
  }

  public abstract boolean blocksPC();

  public abstract boolean blocksNPC();

  public abstract boolean isSafe();

  public int getSprite() {
    return baseSprite;
  }

  public void setSprite(int id) {
    baseSprite = id;
  }

  public abstract char getSymbol();

  @Override
  public String toString() {
    return "" + getSymbol();
  }

  public void tick(){

  }

  public static Tile read(Map map, int x, int y, char data) {
    Tile tile = null;
    if (data == '.') tile = new  DirtTile(map, x, y);
    if (data == '#') tile = new BushTile(map, x, y);
    if (data == 'D') tile = new StoneTile(map, x, y);
    if (data == 'm') tile = new GrassTile(map, x, y);
    if (tile == null) tile = new DefaultTile(map, x, y);
    return tile;
  }

  public void render(Screen screen, int x, int y, int mapBackgroundSprite) {
    if (baseSprite == 0){
      baseSprite = mapBackgroundSprite;
    }
    screen.render(baseSprite, x, y);
    screen.render(maskSprite, x, y);
  }

  public abstract void onEnter(Entity entity);

  public abstract boolean interact(Entity entity, Item item, Dir dir);
}