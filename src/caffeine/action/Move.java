package caffeine.action;

import java.awt.Rectangle;

import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.Tile;

public class Move implements Action {
  protected Direction dir;
  protected Map map;

  public Move(Map map, Direction dir) {
    this.map = map;
    this.dir = dir;
  }

  public boolean dryRun(Entity actor) {
    Loc end = actor.getLoc().copy().translate(dir, actor.getSpeed());
    Tile endTile = map.getTileAt(end.x(), end.y());
    return endTile.canPass();
  }

  @Override
  public boolean performBy(Entity actor) {
    Loc start = actor.getLoc();
    Loc end = start.copy().translate(dir, actor.getSpeed());

    Tile endTile = map.getTileAt(end.x(), end.y());

    if (endTile.canPass()) {
      start.set(end);

      Rectangle hitbox = actor.getHitbox();
      for (Entity e : endTile.occupants()) {
        if (!actor.equals(e) && hitbox.intersects(e.getHitbox())) {
          actor.collideWith(e);
        }
      }
      return true;
    }
    return false;
  }
}
