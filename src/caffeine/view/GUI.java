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
		super("Caffeine Game Engine Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		view = new BoardView(game);
		setContentPane(view);
		
		this.addKeyListener(new KeyInputComponent(game.getInteractionHandler()));
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public BoardView getContentPane(){
		return view;
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
