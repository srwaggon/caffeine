package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.gfx.InputHandler;
import caffeine.world.Dir;

public class InteractiveBrain extends Brain {
  protected InputHandler input;

  public InteractiveBrain(InputHandler input, Mob self) {
    super(self);
    this.input = input;
  }

  @Override
  public void tick() {
    input.tick();
    if (input.up.isPressed) self.move(Dir.N);
    if (input.down.isPressed) self.move(Dir.S);
    if (input.left.isPressed) self.move(Dir.W);
    if (input.right.isPressed) self.move(Dir.E);
    if (input.jump.isClicked) self.jump();
    if (input.use.isClicked) self.useRightHand();
  }

  public InputHandler getInputHandler() {
    return input;
  }

  public void setInputListener(InputHandler input) {
    this.input = input;
  }
}
