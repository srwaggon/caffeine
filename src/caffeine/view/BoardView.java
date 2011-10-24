package caffeine.view;


import java.awt.*;
import javax.swing.*;

import caffeine.Game;
import caffeine.world.Map;
/**
   A panel for displaying the board.
 */

@SuppressWarnings("serial")
public class BoardView extends JPanel{
	private Camera cam;
	private Game game;
	/**
       Creates a view from a board.
	 */
	public BoardView(Game game) {
		this.game = game;
		cam = new Camera(0, 0, game.getCurrentMap().getWidth()*game.getCurrentMap().getTileSize());
		setBackground(Color.WHITE);
	}
	
	public Camera getCamera(){
		return cam;
	}

	/**
       Paints the panel.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		cam.view(g2, game.getCurrentMap().getSprites());
	}

	/**
       Returns the preferred size of this panel.
	 */
	public Dimension getPreferredSize() {
		Map m = game.getCurrentMap();
		int tileSize = m.getTileSize();
		int width = m.getWidth();
		int height = m.getHeight();
		int boardWidth = Math.min(tileSize * width, 1200);
		int boardHeight = Math.min(tileSize * height, 800);
		return new Dimension(boardWidth, boardHeight);
	}  
}

