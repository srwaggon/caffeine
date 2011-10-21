package caffeine.view;

import javax.swing.*;

import caffeine.Game;

/**
   Displays a graphical interface to follow the client's
   connection to the server.
 */

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private BoardView view;
	private int delay;

	/**
       Creates a GUI for the given board.
	 */
	public GUI(Game game) {
		super("Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		view = new BoardView(game.getCurrentMap());
		setContentPane(view);
		
		this.addKeyListener(new KeyInputHandler(game.getInteractionHandler()));
		
		pack();
		setResizable(false);
		setVisible(true);
	}

	/**
       Sets the delay between updates to the given number of
       milliseconds.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
       Updates the display.
	 */
	public void update() {
		try {
			view.repaint();
			if (delay > 0)
				Thread.sleep(delay);
		}
		catch (Throwable ex) { }
	}  
}
