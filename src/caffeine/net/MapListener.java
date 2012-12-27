package caffeine.net;

import caffeine.entity.Entity;
import caffeine.world.tile.Tile;


public interface MapListener {

  public void onAddEntity(Entity entity);

  public void onRemoveEntity(Entity entity);

  public void onTileChange(Tile tile);

}
