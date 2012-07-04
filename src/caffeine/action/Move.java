package caffeine.action;

import java.awt.Rectangle;
import java.util.LinkedList;
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
    double dx = speed * dir.dx();
    double dy = speed * dir.dy();
    Rectangle hitbox = actor.getHitbox();
    hitbox.translate((int) dx, (int) dy);

    // for each corner, if valid tile
    Tile upleft = map.getTileAt(hitbox.x, hitbox.y);
    Tile upright = map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y);
    Tile downleft = map.getTileAt(hitbox.x, hitbox.y + hitbox.height - 1);
    Tile downright = map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y
        + hitbox.height - 1);

    boolean validTiles;
    switch (dir) {
    default:
      validTiles = false;
      break;
    case NORTH:
      validTiles = upleft.canPass() && upright.canPass();
      break;
    case EAST:
      validTiles = upright.canPass() && downright.canPass();
      break;
    case SOUTH:
      validTiles = downleft.canPass() && downright.canPass();
      break;
    case WEST:
      validTiles = upleft.canPass() && downleft.canPass();
      break;
    }

    if (validTiles) {
      // Coral all potential colliders.
      List<Entity> potentialColliders = new LinkedList<Entity>();
      potentialColliders.addAll(upleft.occupants());
      potentialColliders.addAll(upright.occupants());
      potentialColliders.addAll(downleft.occupants());
      potentialColliders.addAll(downright.occupants());

      return true;
    }
    //System.out.println(actor + " can't move from inaccessibility.");
    return false;
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

    // for each corner, if valid tile
    Tile upleft = map.getTileAt(hitboxNext.x, hitboxNext.y);
    Tile upright = map.getTileAt(hitboxNext.x + hitboxNext.width - 1,
        hitboxNext.y);
    Tile downleft = map.getTileAt(hitboxNext.x, hitboxNext.y
        + hitboxNext.height - 1);
    Tile downright = map.getTileAt(hitboxNext.x + hitboxNext.width - 1,
        hitboxNext.y + hitboxNext.height - 1);

    boolean validTiles;
    switch (dir) {
    default:
      validTiles = false;
      break;
    case NORTH:
      validTiles = upleft.canPass() && upright.canPass();
      break;
    case EAST:
      validTiles = upright.canPass() && downright.canPass();
      break;
    case SOUTH:
      validTiles = downleft.canPass() && downright.canPass();
      break;
    case WEST:
      validTiles = upleft.canPass() && downleft.canPass();
      break;
    }

    if (validTiles) {
      // Coral all potential colliders.
      List<Entity> potentialColliders = new LinkedList<Entity>();
      potentialColliders.addAll(upleft.occupants());
      potentialColliders.addAll(upright.occupants());
      potentialColliders.addAll(downleft.occupants());
      potentialColliders.addAll(downright.occupants());

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

      // If each collision successful, move successfully.

      // Vacate old tiles.
      map.getTileAt(hitbox.x, hitbox.y).removeEntity(actor);
      map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y).removeEntity(actor);
      map.getTileAt(hitbox.x, hitbox.y + hitbox.height - 1).removeEntity(actor);
      map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y + hitbox.height - 1)
          .removeEntity(actor);

      // Occupy new tiles.
      upleft.addEntity(actor);
      upright.addEntity(actor);
      downleft.addEntity(actor);
      downright.addEntity(actor);

      // Change location.
      start.set(end);

      return true;
    }
    return false;
  }
}
