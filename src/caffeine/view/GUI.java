package caffeine.view;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import caffeine.Game;
import caffeine.view.screen.Screen;

public class GUI extends JFrame implements Runnable {
  private static final long serialVersionUID = -7225184243885275201L;
  private static int fps = 60;
  protected InputHandler input;
  protected KeyListener focus;
  protected Screen screen;
  
  public GUI(Game game) {
    setTitle(game.toString());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setResizable(false);
    input = new InputHandler(game);
    addKeyListener(input);
  }
  
  public InputHandler input() {
    return input;
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
