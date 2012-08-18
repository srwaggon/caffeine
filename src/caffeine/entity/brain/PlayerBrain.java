package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.entity.Player;
import caffeine.world.World;

public class PlayerBrain extends InteractiveBrain {
  protected Player self;

  public PlayerBrain(Player self) {
    super(self);
    this.self = self;
  }

  public static Mob embody(World world) {
    return new PlayerBrain(new Player(world)).getSelf();
  }

  public void tick(){
    input.tick();
    if (input.up.isPressed) Move.UP.performBy(self);
    if (input.down.isPressed) Move.DOWN.performBy(self);
    if (input.left.isPressed) Move.LEFT.performBy(self);
    if (input.right.isPressed) Move.RIGHT.performBy(self);
    if (input.attack.clicked) self.attack();
    if (input.jump.clicked) self.jump();
  }
}
