package caffeine.action;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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
    // project entity's location & hitbox
    double speed = actor.getSpeed();
    double dx = dir.dx() * speed;
    double dy = dir.dy() * speed;

    Rectangle hitboxNext = actor.getHitbox();
    hitboxNext.translate((int) dx, (int) dy);

    List<Tile> nextTiles = map.getTiles(hitboxNext);

    for (Tile t : nextTiles) {
      if (!t.canPass()) {
        return false;
      }
    }

    return true;
  }



  @Override
  public boolean performBy(Entity actor) {

    // project entity's location & hitbox
    double speed = actor.getSpeed();
    double dx = dir.dx() * speed;
    double dy = dir.dy() * speed;
    Loc start = actor.getLoc();
    Loc end = start.copy().translate(dx, dy);

    Rectangle hitbox = actor.getHitbox();
    Rectangle hitboxNext = actor.getHitbox();
    hitboxNext.translate((int) dx, (int) dy);

    List<Tile> nextTiles = map.getTiles(hitboxNext);
    List<Entity> potentialColliders = new ArrayList<Entity>();

    for (Tile t : nextTiles) {
      if (!t.canPass()) {
        return false;
      } else {
        potentialColliders.addAll(t.occupants());
      }
    }

    // Check collision with each entity.
    for (Entity collider : potentialColliders) {
      Rectangle colliderBox = collider.getHitbox();

      // if they currently intersect, move freely.
      if (collider.equals(actor) || hitbox.intersects(colliderBox)) {
        continue;
      }

      // If they're going to intersect, inform them.
      if (!actor.equals(collider) && hitboxNext.intersects(colliderBox)) {
        // If the collision is bad, the move is unsuccessful.
        return actor.handleCollision(this, collider);
      }
    }

    // Vacate old tiles.
    for (Tile t : map.getTiles(hitbox)){
      t.removeEntity(actor);
    }

    // Occupy new tiles.
    for (Tile t : nextTiles) {
      t.addEntity(actor);
    }

    // Change location.
    start.set(end);

    return true;
  }
}
