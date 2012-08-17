package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Entity;
import caffeine.world.World;

public class PlayerBrain extends InteractiveBrain {

  public PlayerBrain(Entity self) {
    super(self);
  }

  public static Entity embody(World world) {
    return new PlayerBrain(new Entity(world)).getEntity();
  }

  public void tick(){
    if (input.up.isPressed) Move.UP.performBy(self);
    if (input.down.isPressed) Move.DOWN.performBy(self);
    if (input.left.isPressed) Move.LEFT.performBy(self);
    if (input.right.isPressed) Move.RIGHT.performBy(self);
  }
}
