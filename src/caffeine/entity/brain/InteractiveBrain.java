package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.gfx.InputHandler;

public class InteractiveBrain extends Brain {
  protected InputHandler input = new InputHandler();

  public InteractiveBrain(Mob self) {
    super(self);
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
