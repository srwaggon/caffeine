package caffeine.action;

import caffeine.Rule;
import caffeine.entity.Actor;
import caffeine.world.Direction;
import caffeine.world.Location;

public class StaticMotion implements Motion {
  protected Direction facing = Direction.W;
  protected Location loc;
  protected Location newLoc;
  private int xdir = 0;
  private int ydir = 0;
  protected int speed = 2;
  protected int moveTimer;
  protected Rule validLocRule;
  
  public StaticMotion(Location l) {
    loc = l;
    newLoc = l.copy();
    
    validLocRule = new Rule() {
      public boolean appliesTo(Object o) {
        if (o instanceof Location) {
          Location l = (Location) o;
          return l.validLocation() && l.tile().pass();
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
        xdir = xdir == 0 ? -1 : 0;
        break;
      case N:
        ydir = ydir == 0 ? -1 : 0;
        break;
      case E:
        xdir = xdir == 0 ? 1 : 0;
        break;
      case S:
        ydir = ydir == 0 ? 1 : 0;
        break;
      default:
        break;
    }
    
    if (validMove(move, performer)) {
      newLoc.set(loc.project(xdir * speed, ydir * speed));
      facing = d;
    }
  }
  
  public int speed() {
    return speed;
  }
  
  public void speed(int speed) {
    this.speed = speed;
  }
  
  /**
   * Updates the location field by the accumulated (valid) moves.
   */
  public void tick() {
    if (moveTimer <= 0) {
      loc.set(newLoc);
      newLoc.set(loc);
      xdir = 0;
      ydir = 0;
      moveTimer = 0;
    } else {
      moveTimer--;
    }
  }
  
  public boolean validMove(Move move, Actor performer) {
    Direction d = move.dir();
    /* Determine direction(s) of motion, but they can't be conflicting */
    int xdir = 0;
    int ydir = 0;
    switch (d) {
      case W:
        xdir = xdir == 0 ? -1 : 0;
        break;
      case N:
        ydir = ydir == 0 ? -1 : 0;
        break;
      case E:
        xdir = xdir == 0 ? 1 : 0;
        break;
      case S:
        ydir = ydir == 0 ? 1 : 0;
        break;
      default:
        break;
    }
    
    for (Location corner : performer.vertices()) {
      Location projection = corner.project(xdir * speed, ydir * speed);
      if (!validLocRule.appliesTo(projection)) {
        return false;
      }
    }
    return true;
  }
  
  public Rule validLocRule() {
    return validLocRule;
  }
  
  public void validLocRule(Rule r) {
    validLocRule = r;
  }
}
