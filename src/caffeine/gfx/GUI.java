package caffeine.gfx;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import caffeine.InputHandler;

import pixl.Frame;
import pixl.Renderable;

public class GUI implements WindowListener {
  private final Frame frame;
  private final InputHandler input = new InputHandler();
  private final SpriteSheet spritesheet;
  
  public GUI(String title) {
    frame = new Frame(title);
    frame.setTitle(title);
    frame.addWindowListener(this);
    addInputHandler(input);
  }
  
  public void addInputHandler(InputHandler input) {
    frame.addKeyListener(input);
    frame.addMouseListener(input);
    frame.addMouseMotionListener(input);
  }
  
  public void addRenderable(Renderable renderable) {
    frame.addRenderable(renderable);
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
  
  @Override
  public InputHandler getInputHandler() {
    return input;
  }
}
