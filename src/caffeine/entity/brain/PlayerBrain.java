package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.view.InputHandler;

public class PlayerBrain extends InteractiveBrain {

  public PlayerBrain(Game g, Entity owner, InputHandler i) {
    super(g, owner, i);
    actionMap.put(KeyEvent.VK_UP, new Action[] { Move.NORTH, Face.NORTH });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] { Move.SOUTH, Face.SOUTH });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] { Move.WEST, Face.WEST });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] { Move.EAST, Face.EAST });
    // actionMap.put(KeyEvent.VK_SPACE, new Action[] { new CreateProjectile()
    // });
  }
}
