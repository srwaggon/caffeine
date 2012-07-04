package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.Action;
import caffeine.action.Face;
import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.Direction;
import caffeine.world.Map;
import caffeine.world.World;

public class PlayerBrain extends InteractiveBrain {

  public PlayerBrain(Entity self) {
    super(self);
    Map map = self.getWorld().getMap(self.getLoc().mapID);
    actionMap.put(KeyEvent.VK_UP, new Action[] {
        new Move(map, Direction.NORTH), Face.NORTH });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] {
        new Move(map, Direction.SOUTH), Face.SOUTH });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] {
        new Move(map, Direction.WEST), Face.WEST });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] {
        new Move(map, Direction.EAST), Face.EAST });
  }

  public static Entity embody(World world) {
    return new PlayerBrain(new Entity(world)).getEntity();
  }
}
