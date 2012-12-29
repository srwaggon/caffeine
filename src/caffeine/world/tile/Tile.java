package caffeine.world.tile;

import java.io.Serializable;

import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Map;


public class Tile implements Serializable {
  private static final long serialVersionUID = -4410353113874468565L;

  protected int x, y;

  protected TileType type = TileType.dirt;
  protected TileObject tileObject = null;


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
    if (tileObject != null) screen.render(tileObject.getSprite(), x, y);

  }

  public void onEnter(Entity entity) {
  }

  public boolean interact(Entity entity, Item item, Dir dir) {
    //type = TileType.nil;
    if (tileObject != null) {
      tileObject.interact(entity, item, dir);

      if (tileObject.isRemoved()) {
        Item dropped = tileObject.itemDropped();
        if (dropped != null) {
          Entity ie = new ItemEntity(dropped);
          int ts = Map.tileSize;
          ie.setLoc(x*ts + ts/2, y*ts + ts/2);
          entity.getMap().addEntity(ie);
        }
        tileObject = null;
      }
    }
    return true;
  }

  public boolean blocksNPC() {
    if (tileObject != null) {
      return tileObject.blocksNPC();
    }
    return false;
  }

  public boolean blocksPC(){
    if (tileObject != null) {
      return tileObject.blocksPC();
    }
    return false;
  }

  public int getSprite() {
    if (tileObject != null) {
      return tileObject.getSprite();
    }
    return type.getSprite();
  }

  public char getSymbol() {
    if (tileObject != null) {
      return tileObject.getSymbol();
    }
    return type.getChar();
  }

  @Override
  public String toString() {
    return "" + getSymbol();
  }
}