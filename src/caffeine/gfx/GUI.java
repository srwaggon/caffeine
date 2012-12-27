package caffeine.gfx;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class GUI implements WindowListener {
  protected final Frame frame = new Frame();
  public final Screen screen = new Screen();

  public GUI(String title) {
    frame.add(screen);
    frame.setTitle(title);
    frame.addWindowListener(this);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    screen.requestFocus();
  }

  public void addInputListener(InputHandler input) {
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }


  public void windowActivated(WindowEvent e) {
  }

  public void windowClosed(WindowEvent e) {
  }

  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }

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
}
