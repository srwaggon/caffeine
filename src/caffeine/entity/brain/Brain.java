package caffeine.entity.brain;

import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
import caffeine.entity.Entity;

public class Brain {
  protected List<Action> actionPlan = new LinkedList<Action>();
  
  public List<Action> next(Entity entity) {
    return actionPlan;
  }
}
