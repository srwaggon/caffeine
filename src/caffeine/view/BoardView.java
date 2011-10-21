package caffeine.view;


import java.awt.*;
import javax.swing.*;

import caffeine.world.*;
/**
   A panel for displaying the board.
 */

@SuppressWarnings("serial")
public class BoardView extends JPanel{
	private Map board;

	/**
       Creates a view from a board.
	 */
	public BoardView(Map board) {
		this.board = board;
		setBackground(Color.WHITE);
	}

	/**
       Paints the panel.
	 */
	public void paintComponent(Graphics g) {
		board.paint(g);
	}

	/**
       Returns the preferred size of this panel.
	 */
	public Dimension getPreferredSize() {
		int tileSize = board.getTileSize();
		int width = board.getWidth();
		int height = board.getHeight();
		int boardWidth = Math.min(tileSize * width, 1200);
		int boardHeight = Math.min(tileSize * height, 800);
		return new Dimension(boardWidth, boardHeight);
	}  
}

