package caffeine.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
  protected Screen screen = new Screen();

  public GUI(InteractionHandler interactions) {
    super("Caffeine Game Engine Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(screen);
    pack();
    setResizable(false);
    setVisible(true);
    addKeyListener(new KeyInputComponent(interactions));
  }

  public Screen getContentPane(){
    return screen;
  }

}
