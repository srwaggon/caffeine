package caffeine.view;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import caffeine.Game;
import caffeine.view.screen.MenuScreen;
import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;
import caffeine.world.Map;

public class GUI extends JFrame implements Runnable {
  private static final long serialVersionUID = -7225184243885275201L;
  private static int fps = 60;
  protected Screen screen;
  protected InputHandler input;
  protected KeyListener focus;
  
  public GUI(Game game) {
    setTitle(game.toString());
    screen = new MenuScreen();
    setContentPane(screen);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    
    input = new InputHandler(game);
    addKeyListener(input);
  }
  
  public Screen getContentPane() {
    return screen;
  }
  
  public InputHandler input() {
    return input;
  }
  
  public void view(Map map) {
    screen = new WorldScreen(map);
    setContentPane(screen);
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
