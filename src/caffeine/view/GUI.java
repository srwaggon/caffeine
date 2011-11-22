package caffeine.view;

import javax.swing.*;

import caffeine.Game;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private BoardView view;

	public GUI(Game game) {
		super("Caffeine Game Engine Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		view = new BoardView(game);
		setContentPane(view);
		
		this.addKeyListener(new KeyInputComponent(game.interactionHandler()));
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public BoardView getContentPane(){
		return view;
	}  
}
