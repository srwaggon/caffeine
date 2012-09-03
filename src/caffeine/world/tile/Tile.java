package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;


public class Tile {

  protected int x, y;

  protected TileType type = TileType.dirt;
  protected TileObject tileObject = new Bush();


  public Tile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void hold(TileObject tileObject){
    this.tileObject = tileObject;
  }

  public static Tile read(int x, int y, char data) {
    Tile tile = new Tile(x, y);
    if (data == '#') tile.hold(new Bush());
    if (data == 'D') tile.hold(new Stone());
    return tile;
  }

  public void render(Screen screen, int x, int y, int mapBackgroundSprite) {
    screen.render(mapBackgroundSprite, x, y);
    if (tileObject != null){
      screen.render(tileObject.getSprite(), x, y);
    }
  }

  public void onEnter(Entity entity) { }

  public boolean interact(Entity entity, Item item, Dir dir) {
    tileObject.interact(entity, item, dir);

    if (tileObject.isRemoved()){
      if (tileObject.itemDropped())
        new ItemEntity(item, entity.getMap()).moveTo(x, y);
      tileObject = TileObject.Nothing;
    }
    return true;
  }

  public boolean blocksNPC() {
    return tileObject.blocksNPC();
  }

  public boolean blocksPC(){
    return tileObject.blocksPC();
  }

  public int getSprite() {
    return tileObject.getSprite();
  }

  public char getSymbol() {
    return tileObject.getSymbol();
  }

  @Override
  public String toString() {
    return "" + getSymbol();
  }
}