package caffeine.view;

import javax.swing.JFrame;

import caffeine.world.Map;

@SuppressWarnings("serial")
public class GUI extends JFrame {
  protected Screen screen;

  public GUI(Map map, InteractionHandler interactions) {
    screen = new Screen(map);
    setContentPane(screen);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    //setResizable(false);
    setVisible(true);
    addKeyListener(interactions);

  }

  public Screen getContentPane(){
    return screen;
  }

}
