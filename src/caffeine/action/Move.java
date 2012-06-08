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
    /*    int speed = actor.getSpeed();
    Loc end = actor.getLoc().copy().translate(dir.dx()*speed, dir.dy()*speed);
    Rectangle hitbox = (Rectangle) actor.getHitbox().clone();

    hitbox.setLocation((int)end.x, (int)end.y);

    Tile endTile = map.getTileAt((int)end.x, (int)end.y);
    return endTile.canPass();
     */
    // project entity's location & hitbox
    double speed = actor.getSpeed();
    double dx = dir.dx() * speed;
    double dy = dir.dy() * speed;
    Loc start = actor.getLoc();
    Loc end = start.copy().translate(dx, dy);

    Rectangle hitbox = actor.getHitbox();
    hitbox.translate((int)dir.dx(), (int)dir.dy());

    // for each corner, if valid tile
    Tile upleft    = map.getTileAt(hitbox.x,                   hitbox.y);
    Tile upright   = map.getTileAt(hitbox.x + hitbox.width -1, hitbox.y);
    Tile downleft  = map.getTileAt(hitbox.x,                   hitbox.y + hitbox.height -1);
    Tile downright = map.getTileAt(hitbox.x + hitbox.width -1, hitbox.y + hitbox.height -1);

    boolean validTiles;
    switch(dir){
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
      for(Entity collider : potentialColliders){
        // Must not be self, and they must intersect.
        if (actor.equals(collider) || !hitbox.intersects(collider.getHitbox())) {
          continue;
        }

        // If the collision is bad, the move is unsuccessful.
        if(!actor.collideWith(collider)){
          return false;
        }
      }
      return true;
    }
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
    hitbox.translate((int)dir.dx(), (int)dir.dy());

    // for each corner, if valid tile
    Tile upleft    = map.getTileAt(hitbox.x,                   hitbox.y);
    Tile upright   = map.getTileAt(hitbox.x + hitbox.width -1, hitbox.y);
    Tile downleft  = map.getTileAt(hitbox.x,                   hitbox.y + hitbox.height -1);
    Tile downright = map.getTileAt(hitbox.x + hitbox.width -1, hitbox.y + hitbox.height -1);

    boolean validTiles;
    switch(dir){
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
      /*
      upleft.setSpriteID(14);
      upright.setSpriteID(14);
      downleft.setSpriteID(14);
      downright.setSpriteID(14);
       */
      // Coral all potential colliders.
      List<Entity> potentialColliders = new LinkedList<Entity>();
      potentialColliders.addAll(upleft.occupants());
      potentialColliders.addAll(upright.occupants());
      potentialColliders.addAll(downleft.occupants());
      potentialColliders.addAll(downright.occupants());

      // Check collision with each entity.
      for(Entity collider : potentialColliders){
        // Must not be self, and they must intersect.
        if (actor.equals(collider) || !hitbox.intersects(collider.getHitbox())) {
          continue;
        }

        // If the collision is bad, the move is unsuccessful.
        if(!actor.collideWith(collider)){
          return false;
        }
      }

      // If each collision successful, move successfully.
      Tile endTile = map.getTileAt(end);

      //
      hitbox = actor.getHitbox();
      int hx = hitbox.x;
      int hy = hitbox.y;

      // Vacate old tiles.
      map.getTileAt(hitbox.x, hitbox.y).removeEntity(actor);
      map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y).removeEntity(actor);
      map.getTileAt(hitbox.x, hitbox.y + hitbox.height - 1).removeEntity(actor);
      map.getTileAt(hitbox.x + hitbox.width - 1, hitbox.y + hitbox.height - 1).removeEntity(actor);

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
