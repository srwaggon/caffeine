package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Direction;

public class Move implements Action {
  private Direction dir;

  public static final Action NORTH = new Move(Direction.N);
  public static final Action EAST = new Move(Direction.E);
  public static final Action SOUTH = new Move(Direction.S);
  public static final Action WEST = new Move(Direction.W);

  public Move(Direction dir) {
    this.dir = dir;
  }

  @Override
  public void performBy(Entity actor) {
    actor.loc().translate(dir, actor.speed());

    // for each tile under each corner of the entity

    // for each entity in that tile

    // check overlap

    // and if collision, call collide with
  }
}
