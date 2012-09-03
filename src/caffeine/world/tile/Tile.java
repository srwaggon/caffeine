package caffeine.world.tile;

import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Map;


public class Tile {

  protected int x, y;

  protected TileType type = TileType.dirt;
  protected TileObject tileObject = TileObject.Nothing;


  public Tile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void hold(TileObject tileObject){
    this.tileObject = tileObject;
    type = tileObject.type;
  }

  public static Tile read(int x, int y, char data) {
    Tile tile = new Tile(x, y);
    if (data == '#') tile.hold(new Bush());
    if (data == 'D') tile.hold(new Stone());
    return tile;
  }

  public void render(Screen screen, Map map, int x, int y) {

    boolean u = map.getTileAt(x, y - 1).type == type;
    boolean d = map.getTileAt(x, y + 1).type == type;
    boolean l = map.getTileAt(x - 1, y).type == type;
    boolean r = map.getTileAt(x + 1, y).type == type;

    int spriteBase = (type.ordinal() + 1) * 32;
    int spriteOffset = 0;

    if (!u &&  d && !l &&  r) spriteOffset = 1;
    if (!u &&  d &&  l &&  r) spriteOffset = 2;
    if (!u &&  d &&  l && !r) spriteOffset = 3;
    if ( u &&  d && !l &&  r) spriteOffset = 4;
    if ( u &&  d &&  l && !r) spriteOffset = 5;
    if ( u && !d && !l &&  r) spriteOffset = 6;
    if ( u && !d &&  l &&  r) spriteOffset = 7;
    if ( u && !d &&  l && !r) spriteOffset = 8;
    if (!u && !d && !l && !r) spriteOffset = 9;

    screen.render(spriteBase + spriteOffset, x, y);
    screen.render(tileObject.getSprite(), x, y);

  }

  public void onEnter(Entity entity) { }

  public boolean interact(Entity entity, Item item, Dir dir) {
    tileObject.interact(entity, item, dir);

    if (tileObject.isRemoved()){
      if (tileObject.itemDropped())
        // TODO: change magic numbers:
        new ItemEntity(item, entity.getMap()).moveTo(x*Map.tileSize + 8, y*Map.tileSize + 8);
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