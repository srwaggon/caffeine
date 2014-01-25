package caffeine.entity.brain;

import caffeine.InputHandler;
import caffeine.entity.Mob;

public class InteractiveBrain extends Brain {
  private static final long serialVersionUID = -4836711233666644626L;
  protected InputHandler input;

  public InteractiveBrain(InputHandler inputHandler, Mob self) {
    super(self);
    this.input = inputHandler;
  }

  @Override
  public void tick() {
    input.tick();
    if (input.up.isPressed) {
      self.setAccel(0, -1);
    }
    if (input.down.isPressed) {
      self.setAccel(0, 1);
    }
    if (input.left.isPressed) {
      self.setAccel(-1, 0);
    }
    if (input.right.isPressed) {
      self.setAccel(1, 0);
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
