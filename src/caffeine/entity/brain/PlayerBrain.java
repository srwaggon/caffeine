package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.Attack;
import caffeine.action.Move;
import caffeine.view.InteractionHandler;
import caffeine.world.Direction;

public class PlayerBrain extends InteractiveBrain{

  public PlayerBrain(InteractionHandler i){
    super(i);
    actionMap.put(KeyEvent.VK_UP, new Move(Direction.N));
    actionMap.put(KeyEvent.VK_DOWN, new Move(Direction.S));
    actionMap.put(KeyEvent.VK_LEFT, new Move(Direction.W));
    actionMap.put(KeyEvent.VK_RIGHT, new Move(Direction.E));
    actionMap.put(KeyEvent.VK_SPACE, new Attack());
  }
}