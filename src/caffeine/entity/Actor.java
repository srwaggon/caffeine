package caffeine.entity;

import caffeine.action.Action;
import caffeine.action.Motion;
import caffeine.action.instance.StaticMotion;
import caffeine.entity.brain.Brain;
import caffeine.world.Loc;

public class Actor extends Entity {
  public int attack = 1;
  public int health = 10;
  protected boolean isAlive = true;
  protected Brain brain = new Brain();
  protected Motion motion;
  
  public Actor() {
    motion = new StaticMotion(getID(), loc);
  }
  
  public Actor(Loc l) {
    super(l);
    motion = new StaticMotion(getID(), loc);
  }
  
  public Motion motion() {
    return motion;
  }
  
  public boolean alive() {
    return isAlive;
  }
  
  public void alive(Boolean b) {
    isAlive = b;
  }
  
  public void brain(Brain b) {
    brain = b;
  }
  
  public void tick() {
    if (isAlive) {
      for (Action act : brain.next(this)) {
        act.performBy(this);
      }
    }
  }
  
  public int size() {
    return size;
  }
}
