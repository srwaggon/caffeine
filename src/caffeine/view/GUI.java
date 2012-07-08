package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screen.Screen;

public class GUI {
  protected JFrame frame = new JFrame();
  public Screen screen = new Screen();

  public GUI(String title) {
    frame.add(screen);
    frame.setTitle(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    //frame.setResizable(false);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
  }

  public void addInputListener(InputListener listener) {
    frame.addKeyListener(listener);
    frame.addMouseListener(listener);
    frame.addMouseMotionListener(listener);
  }
}
