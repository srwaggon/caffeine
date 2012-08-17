package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Dir;

public class Move implements Action {
  Dir dir;

  public static final Move UP = new Move(Dir.UP);
  public static final Move DOWN = new Move(Dir.DOWN);
  public static final Move LEFT = new Move(Dir.LEFT);
  public static final Move RIGHT = new Move(Dir.RIGHT);

  public Move(Dir dir) {
    this.dir = dir;
  }

  @Override
  public boolean performBy(Entity actor) {
    actor.setDirection(dir);
    return actor.move(actor.getSpeed() * dir.dx(), actor.getSpeed() * dir.dy(), true);
  }
}
