package caffeine.entity.brain;

import caffeine.InputHandler;
import caffeine.entity.Mob;

public class InteractiveBrain extends Brain {
  private static final long serialVersionUID = -4836711233666644626L;
  protected InputHandler input;

  public InteractiveBrain(Mob self, InputHandler inputHandler) {
    super(self);
    this.input = inputHandler;
  }

  @Override
  public void tick() {
    input.tick();
    if (input.up.isPressed) {
    }
    if (input.down.isPressed) {
    }
    if (input.left.isPressed) {
    }
    if (input.right.isPressed) {
    }
    if (input.jump.isClicked) {
      self.jump();
    }
    if (input.use.isClicked) {
      self.useRightHand();
    }
  }

  public InputHandler getInputHandler() {
    return input;
  }

  public void setInputHandler(InputHandler input) {
    this.input = input;
  }
}
