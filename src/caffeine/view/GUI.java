package caffeine.view;

import javax.swing.JFrame;

import caffeine.world.Map;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Runnable {
  Screen screen = null;
  protected InputHandler interactions = new InputHandler();
  
  public GUI(Map map) {
    screen = new Screen(map);
    setContentPane(screen);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    pack();
    addKeyListener(interactions);
    setVisible(true);
  }
  
  public Screen getContentPane() {
    return screen;
  }
  
  public InputHandler getInteractions() {
    return interactions;
  }
  
  @Override
  public void run() {
    try {
      while (true) {
        repaint();
        Thread.sleep(1000 / 30);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
