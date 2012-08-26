package caffeine.action;

import caffeine.entity.Entity;
import caffeine.world.Dir;

public class Move implements Action {
  int xa, ya;
  Dir dir;

  public static final Move UP = new Move(Dir.UP);
  public static final Move DOWN = new Move(Dir.DOWN);
  public static final Move LEFT = new Move(Dir.LEFT);
  public static final Move RIGHT = new Move(Dir.RIGHT);

  public Move(Dir dir) {
    xa = dir.dx();
    ya = dir.dy();
  }

  public Move(int xa, int ya) {
    this.xa = xa;
    this.ya = ya;
  }

  @Override
  public boolean performBy(Entity actor) {
    if (dir != null) actor.setDir(dir);
    int dx = xa * actor.getSpeed();
    int dy = ya * actor.getSpeed();
    return actor.move(dx, dy);
  }
}
