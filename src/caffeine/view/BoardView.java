package caffeine.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
   A panel for displaying the board.
 */

@SuppressWarnings("serial")
public class BoardView extends JPanel{
	private Camera cam;
	/**
       Creates a view from a board.
	 */
	public BoardView() {
		cam = new Camera(getPreferredSize());
		setBackground(Color.BLACK);
	}

	public Camera getCamera(){
		return cam;
	}

	/**
       Paints the panel.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		cam.view(g2);
	}

	/**
       Returns the preferred size of this panel.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}
}

