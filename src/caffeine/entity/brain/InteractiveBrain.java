package caffeine.entity.brain;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import caffeine.action.Action;
import caffeine.action.Report;
import caffeine.entity.Actor;
import caffeine.view.InputHandler;

public class InteractiveBrain extends Brain {
  HashMap<Integer, Action> actionMap = new HashMap<Integer, Action>();
  InputHandler interactions;
  
  /**
   * Creates an InteractiveBrain.  This type of brain responds to keyboard input.
   * @param InputHandler to read keyboard input from.
   */
  public InteractiveBrain(InputHandler ih){
    interactions = ih;
    actionMap.put(KeyEvent.VK_UP, new Report("Up pressed"));
    actionMap.put(KeyEvent.VK_DOWN, new Report("Down pressed"));
    actionMap.put(KeyEvent.VK_LEFT, new Report("Left pressed"));
    actionMap.put(KeyEvent.VK_RIGHT, new Report("Right pressed"));
  }
  
  /**
   * Plans what this brain intends to do on each turn.
   * <br />In this case, determined by keyboard input
   */
  public List<Action> next(Actor actor) {
    actionPlan.clear();
    for(int keyCode : actionMap.keySet()){
      if(interactions.get(keyCode)) {
        actionPlan.add(actionMap.get(keyCode));
      }
    }
    return actionPlan;
  }
  
}
