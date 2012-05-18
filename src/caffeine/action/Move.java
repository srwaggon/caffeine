package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class Move implements Action {
  private Direction dir;

  public static final Action NORTH = new Move(Direction.N);
  public static final Action EAST = new Move(Direction.E);
  public static final Action SOUTH = new Move(Direction.S);
  public static final Action WEST = new Move(Direction.W);

  private Move(Direction dir) {
    this.dir = dir;
  }

  @Override
  public void performBy(Entity performer) {

  }

  public static Move fetch(Direction dir) {
    return null;
  }

}
