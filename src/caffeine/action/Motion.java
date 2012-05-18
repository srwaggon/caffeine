package caffeine.action;

import caffeine.Rule;
import caffeine.entity.Entity;
import caffeine.world.Direction;

public interface Motion {
  
  public void face(Direction dir);
  
  public Direction facing();
  
  public int speed();
  
  public void speed(int speed);
  
  public Rule validLocRule();
  
  public void validLocRule(Rule r);
  
  public boolean validMove(Move m, Entity movingEntity);
  
}
