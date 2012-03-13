package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screen.MenuScreen;
import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;
import caffeine.world.Map;

public class GUI extends JFrame implements Runnable {
  private static final long serialVersionUID = -7225184243885275201L;
  private static int fps = 60;
  Screen screen = null;
  WorldScreen worldScreen = new WorldScreen();
  protected InputHandler input = new InputHandler();
  
  public GUI(String title) {
    setTitle(title);
    screen = new MenuScreen();
    setContentPane(screen);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    addKeyListener(input);
  }
  
  public Screen getContentPane() {
    return screen;
  }
  
  public InputHandler input() {
    return input;
  }
  
  public void view(Map map) {
    worldScreen.setCurrentMap(map);
    screen = worldScreen;
    setContentPane(screen);
    // TODO this is sloppy. Shouldn't have to set this multiple times.
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
