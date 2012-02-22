package caffeine.entity.brain;

import java.util.ArrayList;
import java.util.List;

import caffeine.action.Action;
import caffeine.entity.Actor;

public class Brain {
  List<Action> actionPlan = new ArrayList<Action>();
  
  public List<Action> next(Actor actor) {
    return actionPlan;
  }
}
