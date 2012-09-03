package caffeine.entity.brain;

import caffeine.entity.Player;
import caffeine.gfx.InputHandler;

public class PlayerBrain extends InteractiveBrain {
  protected Player self;

  public PlayerBrain(InputHandler input, Player self) {
    super(input, self);
    this.self = self;
  }

  public void tick(){
    input.tick();
    self.xa = 0;
    self.ya = 0;
    if (input.up.isPressed) self.ya--;
    if (input.down.isPressed) self.ya++;
    if (input.left.isPressed) self.xa--;
    if (input.right.isPressed) self.xa++;
    if (input.use.clicked) self.useRightHand();
    if (input.jump.clicked) self.jump();
  }
}
