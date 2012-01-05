package caffeine.view;

import javax.swing.JFrame;

import caffeine.Game;

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
    addKeyListener(new KeyInputComponent(Game.interactionHandler()));
  }

  public BoardView getContentPane(){
    return view;
  }
}
