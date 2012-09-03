package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.gfx.InputHandler;

public class InteractiveBrain extends Brain {
  protected InputHandler input;

  public InteractiveBrain(InputHandler input, Mob self) {
    super(self);
    this.input = input;
  }

  @Override
  public void tick() {

  }

  public InputHandler getInputHandler() {
    return input;
  }

  public void setInputListener(InputHandler input) {
    this.input = input;
  }
}
