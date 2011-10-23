package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class KeyInputHandler extends JComponent implements KeyListener{
	private static final long serialVersionUID = -2255035832009759362L;
	protected InteractionHandler interactions;
	
	public KeyInputHandler(InteractionHandler ih) {
		super();
		interactions = ih;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		interactions.set(event.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent event) {		
		interactions.set(event.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.print(e.getKeyChar());
	}
}
