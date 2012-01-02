package caffeine.view;

import javax.swing.JFrame;

import caffeine.CaffeineGame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
  private BoardView view;

  public GUI() {
    super("Caffeine Game Engine Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    view = new BoardView();
    setContentPane(view);
    pack();
    setResizable(false);
    setVisible(true);
    addKeyListener(new KeyInputComponent(CaffeineGame.interactionHandler()));
  }

  public BoardView getContentPane(){
    return view;
  }
}
