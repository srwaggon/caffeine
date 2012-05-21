package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Loc;

public class LeftBrain extends Brain {
  private Direction dir = Direction.S;

  public LeftBrain(Game game, Entity owner){
    super(game, owner);
  }

  @Override
  public List<Action> next() {
    Caffeine caff = (Caffeine) game;
    actionPlan.clear();
    Move move = new Move(dir);
    Loc projection = owner.loc().copy();
    projection.translate(dir, owner.speed());

    if (caff.world().getTile(projection).pass()) {
      actionPlan.add(move);
    } else {
      dir = dir.prev();
    }
    return actionPlan;
  }

}
