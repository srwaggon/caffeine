package caffeine.view;

import javax.swing.JFrame;

import caffeine.view.screen.Screen;
import caffeine.view.screen.WorldScreen;

public class GUI extends JFrame{
  protected Screen screen;
  protected static int fps = 1000 / 60;
  protected WorldScreen world = new WorldScreen();

  public GUI(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  public void addInputListener(InputListener listener) {
    addKeyListener(listener);
    addMouseListener(listener);
    addMouseMotionListener(listener);
  }

  public Screen getScreen() {
    return screen;
  }

  public static int fps(){
    return fps;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
    setContentPane(screen);
    pack();
  }

}
