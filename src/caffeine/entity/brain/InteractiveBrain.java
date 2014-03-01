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
      self.north();
    }
    if (input.down.isPressed) {
      self.south();
    }
    if (input.left.isPressed) {
      self.west();
    }
    if (input.right.isPressed) {
      self.east();
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
