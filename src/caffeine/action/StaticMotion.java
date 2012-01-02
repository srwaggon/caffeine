package caffeine.action;

import caffeine.entity.Actor;
import caffeine.world.Direction;
import caffeine.world.Location;

public class StaticMotion implements Motion{
  protected Location loc;
  protected Location newLoc;
  protected Direction facing = Direction.W;
  private int xdir = 0;
  private int ydir = 0;
  public int speed = 4;
  double accelerationRate = 0;
  int moveTimer;

  public StaticMotion(Location l){
    loc = l;
    newLoc = l.copy();
  }

  public Direction facing() {
    return facing;
  }

  public void handleMove(Move m, Actor performer){
    if(validMove(m, performer)){
      Direction d = m.dir();
      facing = d;

      /* Determine direction(s) of motion, but they can't be conflicting */
      switch (d) {
        case  W: xdir = xdir == 0 ? -1 : 0; break;
        case  N: ydir = ydir == 0 ? -1 : 0; break;
        case  E: xdir = xdir == 0 ?  1 : 0; break;
        case  S: ydir = ydir == 0 ?  1 : 0; break;
        default: break;
      }
      newLoc.set(loc.project(xdir*speed, ydir*speed));
    }
  }


  /**
   * Determines if a given move is valid for a given actor, according to this motion.
   */
  public boolean validMove(Move m, Actor performer){
    int xdirTest = xdir;
    int ydirTest = ydir;

    /* Determine what this move is adjusting */
    Direction d = m.dir();
    switch (d) {
      case  W: xdirTest = -1; break;
      case  N: ydirTest = -1; break;
      case  E: xdirTest =  1; break;
      case  S: ydirTest =  1; break;
      default: break;
    }

    /* For each vertex of the performing Actor,
     * determine if it is a real-space location as well as able to be passed. */
    for(Location l : performer.vertices()){
      Location tempLoc = l.project(xdirTest*speed, ydirTest*speed);
      if(!tempLoc.validLocation() || !tempLoc.tile().pass()){
        return false;
      }
    }

    return true;
  }

  /**
   * Updates the location field by the accumulated (valid) moves.
   */
  public void tick(){
    if(moveTimer <= 0){
      loc.set(newLoc);
      newLoc.set(loc);
      xdir = 0;
      ydir = 0;
      moveTimer = 0;
    }else{
      moveTimer--;
    }
  }
}
