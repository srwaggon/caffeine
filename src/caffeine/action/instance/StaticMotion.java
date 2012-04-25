package caffeine.action.instance;

import java.awt.Rectangle;

import caffeine.Rule;
import caffeine.action.Motion;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Tile;

public class StaticMotion implements Motion {
  protected Direction facing = Direction.pickOneAtRandom();
  protected Loc loc;
  protected Loc newLoc;
  protected int speed = 2;
  protected int moveTimer;
  protected Rule validLocRule;
  
  public StaticMotion(final int ownerID, Loc l) {
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
  
  public int speed() {
    return speed;
  }
  
  public void speed(int speed) {
    this.speed = speed > 0 ? speed : this.speed;
  }
  
  public boolean validMove(Move move, Entity performer) {
    
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
    
    Rectangle projectedHB = performer.hitbox().getBounds();
    projectedHB.translate(dx, dy);
    
    Loc projectedLoc = performer.loc().copy();
    projectedLoc.translate(dx, dy);
    
    return validLocRule.appliesTo(projectedHB) && projectedLoc.isValid();
  }
  
  public Rule validLocRule() {
    return validLocRule;
  }
  
  public void validLocRule(Rule r) {
    validLocRule = r;
  }
  
  // TODO make facing a property of entities/actors
  @Override
  public void face(Direction dir) {
    facing = dir;
  }
}
