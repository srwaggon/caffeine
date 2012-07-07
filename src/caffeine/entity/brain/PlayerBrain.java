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
        Face.NORTH, new Move(Direction.NORTH) });
    actionMap.put(KeyEvent.VK_DOWN, new Action[] {
        Face.SOUTH, new Move(Direction.SOUTH) });
    actionMap.put(KeyEvent.VK_LEFT, new Action[] {
        Face.WEST, new Move(Direction.WEST) });
    actionMap.put(KeyEvent.VK_RIGHT, new Action[] {
        Face.EAST, new Move(Direction.EAST) });
  }

  public static Entity embody(World world) {
    return new PlayerBrain(new Entity(world)).getEntity();
  }
}
