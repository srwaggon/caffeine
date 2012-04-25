package caffeine.action.instance;

import java.awt.Rectangle;

import caffeine.action.Action;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Tile;

/**
 * Represents a change-in-location action which can be performed on (by) Actor
 * objects.
 * 
 * @author srwaggon
 * 
 */
public class Move implements Action {
  protected Direction dir;
  public static Move NORTH = new Move(Direction.N);
  public static Move EAST = new Move(Direction.E);
  public static Move SOUTH = new Move(Direction.S);
  public static Move WEST = new Move(Direction.W);
  
  private Move(Direction d) {
    dir = d;
  }
  
  public Direction dir() {
    return dir;
  }
  
  public static Move fetch(Direction dir) {
    switch (dir) {
      case N:
        return Move.NORTH;
      case E:
        return Move.EAST;
      case S:
        return Move.SOUTH;
      default:
        return Move.WEST;
    }
  }
  
  public void performBy(Entity performer) {
    int speed = performer.motion().speed();
    int dx = 0;
    int dy = 0;
    
    switch (dir) {
      case W:
        dx = -speed;
        break;
      case N:
        dy = -speed;
        break;
      case E:
        dx = speed;
        break;
      case S:
        dy = speed;
        break;
      default:
        break;
    }
    
    Rectangle projection = performer.hitbox().getBounds();
    projection.translate(dx, dy);
    
    if (performer.motion().validLocRule().appliesTo(projection)) {
      Tile oldTile = performer.tile();
      
      performer.loc().translate(dx, dy);
      
      Tile newTile = performer.tile();
      if (!oldTile.equals(newTile)) {
        oldTile.remove(performer);
        newTile.add(performer);
      }
    }
  }
  
  public String toString() {
    return "move: " + dir;
  }
  
}
