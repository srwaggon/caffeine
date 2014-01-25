package caffeine.gfx;

import pixl.Frame;
import pixl.Renderable;
import pixl.Spritesheet;
import caffeine.InputHandler;

public class GUI {

  private final Frame frame;
  private final Spritesheet spritesheet = new Spritesheet("sprites2.png", 16);
  private InputHandler inputHandler = new InputHandler();

  public GUI(String title) {
    frame = new Frame(title);
    frame.addInputListener(inputHandler);
  }

  public void addRenderable(Renderable renderable) {
    frame.addRenderable(renderable);
  }

  public void start() {
    frame.start();
  }

  public InputHandler getInputHandler() {
    return inputHandler;
  }
}
