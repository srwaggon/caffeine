package caffeine.view;

import javax.swing.JFrame;

import caffeine.Server;

@SuppressWarnings("serial")
public class GUI extends JFrame {
  protected Screen screen = new Screen();

  public GUI() {
    super("Caffeine Game Engine Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(screen);
    pack();
    //setResizable(false);
    setVisible(true);
    addKeyListener(new KeyInputComponent(Server.interactionHandler()));
  }

  public Screen getContentPane(){
    return screen;
  }

}
