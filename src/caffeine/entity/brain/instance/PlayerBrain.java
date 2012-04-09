package caffeine.entity.brain.instance;

import java.awt.event.KeyEvent;

import caffeine.action.Action;
import caffeine.action.instance.CreateProjectile;
import caffeine.action.instance.Face;
import caffeine.action.instance.Move;
import caffeine.entity.brain.InteractiveBrain;
import caffeine.view.InputHandler;

public class PlayerBrain extends InteractiveBrain {
  
  public PlayerBrain(InputHandler i) {
    super(i);
    actionMap.put(KeyEvent.VK_UP, new Action[] { Move.NORTH, Face.NORTH });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] { Move.SOUTH, Face.SOUTH });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] { Move.WEST, Face.WEST });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] { Move.EAST, Face.EAST });
    actionMap.put(KeyEvent.VK_SPACE, new Action[] { new CreateProjectile() });
  }
}
