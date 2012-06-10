package caffeine.entity.brain;

import java.util.List;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;


public class StraightBrain extends Brain {
  protected Direction forward;

  public StraightBrain(Game game, Entity self) {
    super(game, self);
    forward = self.getFacing();
  }

  public void setForward(Direction dir){
    forward = dir;
  }

  @Override
  public List<Action> next() {
    actionPlan.clear();

    Caffeine caff = (Caffeine) game;
    actionPlan.clear();
    Map currentMap = caff.world().getMap(self.getLoc().mapID);
    Move next = new Move(currentMap, forward);

    // TODO: Requiring dry-runs allows entities to attempt to perform
    // invalid moves.
    if(!next.dryRun(self)){
      forward = forward.opposite();
    }

    actionPlan.add(next);
    return actionPlan;
  }


}
