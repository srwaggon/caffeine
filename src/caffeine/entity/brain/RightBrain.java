package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

public class RightBrain extends Brain {
  Direction dir = Direction.EAST;

  public RightBrain(Game g, Entity owner) {
    super(g, owner);
  }

  @Override
  public List<Action> next() {
    Caffeine caff = (Caffeine) game;
    actionPlan.clear();
    Map map = caff.world().getMap(self.getLoc().mapID);
    Move move = new Move(map, dir);

    if (move.dryRun(self)) {
      actionPlan.add(move);
    } else {
      dir = dir.next();
    }
    return actionPlan;
  }

}
