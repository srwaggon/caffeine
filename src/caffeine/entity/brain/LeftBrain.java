package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;

public class LeftBrain extends Brain {
  private Direction dir = Direction.S;

  public LeftBrain(Game game, Entity owner) {
    super(game, owner);
  }

  @Override
  public List<Action> next() {
    Caffeine caff = (Caffeine) game;
    actionPlan.clear();
    Map map = caff.world().getMap(owner.getLoc().mapID());

    Move move = new Move(map, dir);

    if (move.dryRun(owner)) {
      actionPlan.add(move);
    } else {
      dir = dir.prev();
    }
    return actionPlan;
  }
}
