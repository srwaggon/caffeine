package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.view.InputHandler;
import caffeine.world.Direction;
import caffeine.world.Map;

public class PlayerBrain extends InteractiveBrain {
  
  public PlayerBrain(Game g, Entity self, InputHandler i) {
    super(g, self, i);
    Caffeine caff = (Caffeine) game;
    Map map = caff.world().getMap(self.getLoc().mapID());
    actionMap.put(KeyEvent.VK_UP, new Action[] { new Move(map, Direction.N),
        Face.NORTH });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] { new Move(map, Direction.S),
        Face.SOUTH });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] { new Move(map, Direction.W),
        Face.WEST });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] { new Move(map, Direction.E),
        Face.EAST });
    // actionMap.put(KeyEvent.VK_SPACE, new Action[] { new CreateProjectile()
    // });
  }
}
