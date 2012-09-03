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
    if (data == 'm') tile.type = TileType.grass;
    if (data == '#') tile.hold(new Bush());
    if (data == 'D') tile.hold(new Stone());
    if (data == '~') tile.type = TileType.water;
    return tile;
  }

  public void render(Screen screen, Map map, int x, int y) {

    boolean u = map.getTileSafe(this.x, this.y - 1).type == type;
    boolean d = map.getTileSafe(this.x, this.y + 1).type == type;
    boolean l = map.getTileSafe(this.x - 1, this.y).type == type;
    boolean r = map.getTileSafe(this.x + 1, this.y).type == type;

    int sprite = type.getSprite();

    if (sprite >= 32) {
      if (!u &&  d && !l &&  r) sprite += 1;
      if (!u &&  d &&  l &&  r) sprite += 2;
      if (!u &&  d &&  l && !r) sprite += 3;
      if ( u &&  d && !l &&  r) sprite += 4;
      if ( u &&  d &&  l && !r) sprite += 5;
      if ( u && !d && !l &&  r) sprite += 6;
      if ( u && !d &&  l &&  r) sprite += 7;
      if ( u && !d &&  l && !r) sprite += 8;
      if (!u && !d && !l && !r) sprite += 9;
      if ( u &&  d && !l && !r) sprite += 10;
      if (!u && !d &&  l &&  r) sprite += 11;
      if (!u && !d &&  l && !r) sprite += 12;
      if (!u && !d && !l &&  r) sprite += 13;
      if ( u && !d && !l && !r) sprite += 14;
      if (!u &&  d && !l && !r) sprite += 15;
    }

    screen.render(sprite, x, y);
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