package caffeine.entity.brain;

import pixl.InputHandler;
import caffeine.entity.Mob;

public class InteractiveBrain extends Brain {
  protected InputHandler input;
  
  public InteractiveBrain(InputHandler input, Mob self) {
    super(self);
    this.input = input;
  }
  
  @Override
  public void tick() {
    input.tick();
    if (input.up.isPressed)
      self.setAccel(0, -1);
    if (input.down.isPressed)
      self.setAccel(0, 1);
    if (input.left.isPressed)
      self.setAccel(-1, 0);
    if (input.right.isPressed)
      self.setAccel(1, 0);
    if (input.jump.isClicked)
      self.jump();
    if (input.use.isClicked)
      self.useRightHand();
  }
  
  public InputHandler getInputHandler() {
    return input;
  }
  
  public void setInputListener(InputHandler input) {
    this.input = input;
  }
}
