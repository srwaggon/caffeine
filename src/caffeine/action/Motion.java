package caffeine.action;

import caffeine.entity.Actor;
import caffeine.world.Direction;

public interface Motion {

  public Direction facing();

  public void handleMove(Move move, Actor target);

  public void tick();

  public boolean validMove(Move m, Actor a);
}
