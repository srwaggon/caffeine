package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screen.Screen;

public class GUI extends JFrame implements Runnable {
  private static final long serialVersionUID = -7225184243885275201L;
  private static int fps = 60;
  protected Screen screen;
  
  public GUI(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setResizable(false);
  }
  
  public Screen getScreen() {
    return screen;
  }
  
  public void setScreen(Screen screen) {
    this.screen = screen;
    setContentPane(screen);
    pack();
  }
  
  public static int fps() {
    return GUI.fps;
  }
  
  public void run() {
    setVisible(true);
    try {
      while (true) {
        repaint();
        Thread.sleep(1000 / GUI.fps);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
