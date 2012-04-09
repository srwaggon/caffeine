package caffeine.entity.brain;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import caffeine.action.Action;
import caffeine.action.instance.Report;
import caffeine.entity.Actor;
import caffeine.view.InputHandler;

public class InteractiveBrain extends Brain {
  protected HashMap<Integer, Action[]> actionMap = new HashMap<Integer, Action[]>();
  protected InputHandler input;
  
  /**
   * Creates an InteractiveBrain. This type of brain responds to keyboard input.
   * 
   * @param InputHandler
   *          to read keyboard input from.
   */
  public InteractiveBrain(InputHandler ih) {
    input = ih;
    actionMap.put(KeyEvent.VK_UP, new Action[] { new Report("Up pressed") });
    actionMap
        .put(KeyEvent.VK_DOWN, new Action[] { new Report("Down pressed") });
    actionMap
        .put(KeyEvent.VK_LEFT, new Action[] { new Report("Left pressed") });
    actionMap.put(KeyEvent.VK_RIGHT,
        new Action[] { new Report("Right pressed") });
  }
  
  /**
   * Plans what this brain intends to do on each turn. <br />
   * In this case, determined by keyboard input
   */
  public List<Action> next(Actor actor) {
    actionPlan.clear();
    for (int keyCode : actionMap.keySet()) {
      if (input.get(keyCode)) {
        actionPlan.addAll(Arrays.asList(actionMap.get(keyCode)));
      }
    }
    return actionPlan;
  }
  
}
