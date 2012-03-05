package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.CreateProjectile;
import caffeine.action.Move;
import caffeine.view.InputHandler;

public class PlayerBrain extends InteractiveBrain {
  
  public PlayerBrain(InputHandler i) {
    super(i);
    actionMap.put(KeyEvent.VK_UP, Move.NORTH);
    actionMap.put(KeyEvent.VK_DOWN, Move.SOUTH);
    actionMap.put(KeyEvent.VK_LEFT, Move.WEST);
    actionMap.put(KeyEvent.VK_RIGHT, Move.EAST);
    actionMap.put(KeyEvent.VK_SPACE, new CreateProjectile());
  }
}
