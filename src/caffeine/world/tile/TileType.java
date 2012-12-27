package caffeine.world.tile;

public enum TileType {
  dirt('.'), grass('m'), water('~'), sand('s'), nil(' ');

  protected char symbol;

  TileType(char c) {
    symbol = c;
  }

  public int getSprite(){
    return this == nil? 0 : this == dirt ? 1 : ordinal() * 32;
  }

  public char getChar(){
    return symbol;
  }
}
