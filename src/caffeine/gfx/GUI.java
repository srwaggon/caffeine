package caffeine.gfx;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import pixl.Renderable;

public class GUI implements WindowListener {
  protected final Frame frame = new Frame();
  protected final List<Renderable> renderables = new ArrayList<Renderable>();
  protected final Screen screen = new Screen();
  protected final InputHandler input = new InputHandler();
  
  public GUI(String title) {
    frame.add(screen);
    frame.setTitle(title);
    frame.addWindowListener(this);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    addInputHandler(input);
    screen.requestFocus();
  }
  
  public void addInputHandler(InputHandler input) {
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }
  
  public Screen getScreen() {
    return screen;
  }
  
  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
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
  
  public InputHandler getInputHandler() {
    return input;
  }
}
