package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.CreateProjectile;
import caffeine.action.Move;
import caffeine.view.InputHandler;
import caffeine.world.Direction;

public class PlayerBrain extends InteractiveBrain {
  
  public PlayerBrain(InputHandler i) {
    super(i);
    actionMap.put(KeyEvent.VK_UP, new Move(Direction.N));
    actionMap.put(KeyEvent.VK_DOWN, new Move(Direction.S));
    actionMap.put(KeyEvent.VK_LEFT, new Move(Direction.W));
    actionMap.put(KeyEvent.VK_RIGHT, new Move(Direction.E));
    actionMap.put(KeyEvent.VK_SPACE, new CreateProjectile());
  }
}
