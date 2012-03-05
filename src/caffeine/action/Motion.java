package caffeine.action;

import caffeine.Rule;
import caffeine.entity.Actor;
import caffeine.world.Direction;

public interface Motion {
  
  public Direction facing();
  
  public void handleMove(Move move, Actor target);
  
  public void move(Actor performer);
  
  public int speed();
  
  public void speed(int speed);
  
  public Rule validLocRule();
  
  public void validLocRule(Rule r);
  
  public boolean validMove(Move m, Actor a);
  
}
