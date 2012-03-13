package caffeine.action.instance;

import java.awt.Rectangle;

import caffeine.Rule;
import caffeine.action.Motion;
import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Location;
import caffeine.world.Tile;

public class StaticMotion implements Motion {
  protected Direction facing = Direction.pickOneAtRandom();
  protected Location loc;
  protected Location newLoc;
  private int dx = 0;
  private int dy = 0;
  protected int speed = 2;
  protected int moveTimer;
  protected Rule validLocRule;
  
  public StaticMotion(final int ownerID, Location l) {
    loc = l;
    newLoc = l.copy();
    validLocRule = new Rule() {
      
      public boolean appliesTo(Object o) {
        
        if (o instanceof Rectangle) {
          Rectangle projection = (Rectangle) o;
          for (Tile t : loc.map().getOverlappingTiles(projection)) {
            t.spriteID(10);
            for (Entity e : t.occupants()) {
              if (e.getID() != ownerID && projection.intersects(e.hitbox())) {
                return false;
              }
            }
          }
          return true;
        }
        return false;
      }
    };
  }
  
  public Direction facing() {
    return facing;
  }
  
  public void handleMove(Move move, Actor performer) {
    Direction d = move.dir();
    
    /* Determine direction(s) of motion, but they can't be conflicting */
    switch (d) {
      case W:
        dx = dx == 0 ? -speed : 0;
        break;
      case N:
        dy = dy == 0 ? -speed : 0;
        break;
      case E:
        dx = dx == 0 ? speed : 0;
        break;
      case S:
        dy = dy == 0 ? speed : 0;
        break;
      default:
        break;
    }
    
    facing = d;
    if (validMove(move, performer)) {
      newLoc.set(loc.project(dx, dy));
    }
  }
  
  public int speed() {
    return speed;
  }
  
  public void speed(int speed) {
    this.speed = speed > 0 ? speed : this.speed;
  }
  
  /**
   * Updates the location field by the accumulated (valid) moves.
   */
  public void move(Actor owner) {
    if (moveTimer <= 0) {
      owner.loc().tile().remove(owner);
      loc.set(newLoc);
      newLoc.set(loc);
      owner.loc().tile().add(owner);
      dx = 0;
      dy = 0;
      moveTimer = 0;
    } else {
      moveTimer--;
    }
  }
  
  public boolean validMove(Move move, Actor performer) {
    
    int dx = 0;
    int dy = 0;
    
    switch (move.dir()) {
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
    
    return validLocRule.appliesTo(projection);
  }
  
  public Rule validLocRule() {
    return validLocRule;
  }
  
  public void validLocRule(Rule r) {
    validLocRule = r;
  }
}
