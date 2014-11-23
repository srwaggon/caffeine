package caffeine.gfx;

import java.util.concurrent.Callable;

import pixl.Frame;
import pixl.Renderable;
import pixl.Spritesheet;
import caffeine.InputHandler;

public class GUI implements Callable<Void> {

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

  public InputHandler getInputHandler() {
    return inputHandler;
  }

  @Override
  public Void call() throws Exception {
    return frame.call();
  }
}
