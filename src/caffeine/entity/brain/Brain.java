package caffeine.entity.brain;

import java.util.List;

import caffeine.action.Action;
import caffeine.entity.Actor;

/**
 * The basic
 * @author Fnar
 *
 */
public interface Brain {

  public List<Action> next(Actor actor);
}
