package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screens.MenuScreen;
import caffeine.view.screens.Screen;
import caffeine.view.screens.WorldScreen;
import caffeine.world.Map;

public class GUI extends JFrame implements Runnable {
  private static final long serialVersionUID = -7225184243885275201L;
  Screen screen = null;
  WorldScreen worldScreen = new WorldScreen();
  protected InputHandler interactions = new InputHandler();
  
  public GUI(String title) {
    setTitle(title);
    screen = new MenuScreen();
    setContentPane(screen);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    pack();
    addKeyListener(interactions);
  }
  
  public Screen getContentPane() {
    return screen;
  }
  
  public InputHandler getInteractions() {
    return interactions;
  }
  
  public void view(Map map) {
    worldScreen.setCurrentMap(map);
    setContentPane(worldScreen);
  }
  
  public void run() {
    setVisible(true);
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
