package caffeine.world.tile;

public enum TileType {
  DIRT('.'), GRASS('m'), WATER('~'), SAND('s'), nil(' ');

  protected char symbol;

  TileType(char c) {
    symbol = c;
  }

  public int getSprite(){
    return this == nil? 0 : this == DIRT ? 1 : ordinal() * 32;
  }

  public char getChar(){
    return symbol;
  }

  public static void tick(Tile tile) {
    if (tile.type == GRASS && tile.tileObject == null) {
      if (tile.getAge() > 15000 ) {
        tile.hold(new Bush());
      }
    }
  }
}
