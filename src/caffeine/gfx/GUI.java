package caffeine.gfx;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import pixl.Frame;
import pixl.Renderable;
import pixl.Spritesheet;
import pixl.interaction.InputListener;
import caffeine.InputHandler;

public class GUI implements WindowListener {

  private final Frame frame;
  private final Spritesheet spritesheet = new Spritesheet("sprites2.png", 16);
  private InputHandler inputHandler = new InputHandler();

  public GUI(String title) {
    frame = new Frame(title);
    frame.addWindowListener(this);
    frame.addInputListener(inputHandler);
  }

  public void addRenderable(Renderable renderable) {
    frame.addRenderable(renderable);
  }

  public void start() {
    frame.start();
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowClosed(WindowEvent e) {
  }

  @Override
  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
  }

  @Override
  public void windowIconified(WindowEvent e) {
  }

  @Override
  public void windowOpened(WindowEvent e) {
  }

  public InputListener getInputListener() {
    return inputHandler;
  }
}
