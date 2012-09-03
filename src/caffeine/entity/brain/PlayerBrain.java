package caffeine.entity.brain;

import caffeine.action.Move;
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
    int xa = 0;
    int ya = 0;
    if (input.up.isPressed) ya--;
    if (input.down.isPressed) ya++;
    if (input.left.isPressed) xa--;
    if (input.right.isPressed) xa++;
    if (input.use.clicked) self.useRightHand();
    if (input.jump.clicked) self.jump();
    new Move(xa, ya).performBy(self);
  }
}
