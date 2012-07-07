package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public interface Motion {

  public void setDirection(Direction dir);

  public Direction getDirection();

  public double getSpeed();

  public boolean isMoving();

  public void isMoving(boolean b);

  public void setSpeed(double speed);

  public boolean updateLoc(Entity actor);
}
