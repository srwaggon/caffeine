package caffeine.world.tile;


public enum TileType {
  dirt, grass, water, sand,;

  public int getSprite(){
    return this == dirt ? 1 : ordinal() * 32;
  }
}
