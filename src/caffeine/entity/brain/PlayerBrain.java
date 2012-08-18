package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.world.World;

public class PlayerBrain extends InteractiveBrain {

  public PlayerBrain(Mob self) {
    super(self);
  }

  public static Mob embody(World world) {
    return new PlayerBrain(new Mob(world)).getSelf();
  }

  public void tick(){
    if (input.up.isPressed) Move.UP.performBy(self);
    if (input.down.isPressed) Move.DOWN.performBy(self);
    if (input.left.isPressed) Move.LEFT.performBy(self);
    if (input.right.isPressed) Move.RIGHT.performBy(self);
  }
}
