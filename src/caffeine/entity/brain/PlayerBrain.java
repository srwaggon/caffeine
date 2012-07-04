package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.action.Report;
import caffeine.entity.Entity;
import caffeine.view.InputHandler;
import caffeine.world.Direction;
import caffeine.world.Map;
import caffeine.world.World;

public class PlayerBrain extends InteractiveBrain {

  public PlayerBrain(Entity self, InputHandler ih) {
    super(self, ih);
    Map map = self.getWorld().getMap(self.getLoc().mapID);
    actionMap.put(KeyEvent.VK_UP, new Action[] {
        new Move(map, Direction.NORTH), Face.NORTH, new Report("North") });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] {
        new Move(map, Direction.SOUTH), Face.SOUTH });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] {
        new Move(map, Direction.WEST), Face.WEST });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] {
        new Move(map, Direction.EAST), Face.EAST });
    // actionMap.put(KeyEvent.VK_SPACE, new Action[] { new CreateProjectile()
    // });
  }

  public static Entity embody(World world, InputHandler ih) {
    return new PlayerBrain(new Entity(world), ih).getEntity();
  }
}
