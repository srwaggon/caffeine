package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class KeyInputComponent extends JComponent implements KeyListener{
	private static final long serialVersionUID = -2255035832009759362L;
	protected InteractionHandler interactions;

	public KeyInputComponent(InteractionHandler ih) {
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
		System.err.print(e.getKeyChar());
	}
}
