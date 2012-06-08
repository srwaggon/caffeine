package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
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

  public LeftBrain(Game game, Entity self) {
    super(game, self);
  }

  @Override
  public List<Action> next() {
    Caffeine caff = (Caffeine) game;
    actionPlan.clear();
    Map map = caff.world().getMap(self.getLoc().mapID);

    Move move = new Move(map, dir);
    Loc selfLoc = self.getLoc();
    Tile selfTile = map.getTileAt(selfLoc);

    if (turnThresh < Math.random()) {
      dir = dir.prev();
    }

    if (move.dryRun(self)) {
      /*
      if (map.hasNeighbor(selfTile, dir)) {
      if (map.getNeighbor(selfTile, dir).canPass()) {
       */
      actionPlan.add(move);
    } else {
      dir = dir.prev();
    }
    return actionPlan;
  }
}
