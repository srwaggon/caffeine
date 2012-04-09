package caffeine.entity.brain;

import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
import caffeine.entity.Actor;

public class Brain {
  protected List<Action> actionPlan = new LinkedList<Action>();
  
  public List<Action> next(Actor actor) {
    return actionPlan;
  }
}
