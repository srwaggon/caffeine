package caffeine.world.tile;


public enum TileType {
  dirt, grass, water, sand,;

  private static char[] symbols = {'.', 'm', '~', 's'};

  public int getSprite(){
    return this == dirt ? 1 : ordinal() * 32;
  }

  public char getChar(){
    return symbols[ordinal()];
  }
}
