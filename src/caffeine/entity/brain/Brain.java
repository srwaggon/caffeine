package caffeine.entity.brain;

import java.util.LinkedList;
import java.util.List;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.entity.Entity;

public abstract class Brain {
  protected List<Action> actionPlan = new LinkedList<Action>();
  protected Game game;
  protected Entity owner;

  public Brain(Game game, Entity owner){
    this.game = game;
    this.owner = owner;
  }

  public List<Action> next() {
    return actionPlan;
  }
}
