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
		if(event.getKeyCode() == KeyEvent.VK_DOWN){
			interactions.set("down",true);
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT){
			interactions.set("left",true);
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT){
			interactions.set("right",true);
		}
		if(event.getKeyCode() == KeyEvent.VK_UP){
			interactions.set("up",true);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {		
		if(event.getKeyCode() == KeyEvent.VK_DOWN){
			interactions.set("down",false);
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT){
			interactions.set("left",false);
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT){
			interactions.set("right",false);
		}
		if(event.getKeyCode() == KeyEvent.VK_UP){
			interactions.set("up",false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("keyTyped");
	}

}
