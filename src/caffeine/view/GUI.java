package caffeine.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
  protected Screen screen = new Screen();

  public GUI(String title, InteractionHandler interactions) {
    super(title);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(screen);
    pack();
    setResizable(false);
    setVisible(true);
    addKeyListener(interactions);
  }

  public Screen getContentPane(){
    return screen;
  }

}
