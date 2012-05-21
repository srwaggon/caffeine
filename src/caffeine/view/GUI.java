package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;

public class GUI implements Runnable {
  private static int fps = 60;
  private final JFrame frame = new JFrame();
  protected Screen screen;
  protected WorldScreen world = new WorldScreen();
  
  public GUI(String title) {
    frame.setTitle(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setResizable(false);
  }
  
  public void addInputHandler(InputHandler ih) {
    frame.addKeyListener(ih);
  }
  
  public Screen getScreen() {
    return screen;
  }
  
  public void setScreen(Screen screen) {
    this.screen = screen;
    frame.setContentPane(screen);
    frame.pack();
  }
  
  public static int fps() {
    return GUI.fps;
  }
  
  @Override
  public void run() {
    frame.setVisible(true);
    try {
      while (true) {
        frame.repaint();
        Thread.sleep(1000 / GUI.fps);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
