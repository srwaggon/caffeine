package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.Tile;

public class LeftBrain extends Brain {
  private Direction dir = Direction.SOUTH;
  private double turnThresh = .99;

  public LeftBrain(Entity self) {
    super(self);
  }

  @Override
  public List<Action> next() {

    actionPlan.clear();

    Map map = self.getWorld().getMap(self.getLoc().mapID);

    Move move = new Move(dir);
    Loc selfLoc = self.getLoc();
    Tile selfTile = map.getTileAt(selfLoc);

    if (turnThresh < Math.random()) {
      dir = dir.prev();
    }

    actionPlan.add(move);

    return actionPlan;
  }
}
