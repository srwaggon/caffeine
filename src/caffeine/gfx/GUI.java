package caffeine.gfx;

import javax.swing.JFrame;


public class GUI {
  protected JFrame frame = new JFrame();
  public Screen screen = new Screen();

  public GUI(String title) {
    frame.add(screen);
    frame.setTitle(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
