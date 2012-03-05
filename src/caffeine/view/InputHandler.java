package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class InputHandler extends JComponent implements KeyListener {
  HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
  
  public boolean get(int keyCode) {
    if (keys.containsKey(keyCode)) {
      return keys.get(keyCode);
    }
    return false;
  }
  
  public void keyPressed(KeyEvent event) {
    keys.put(event.getKeyCode(), true);
  }
  
  public void keyReleased(KeyEvent event) {
    keys.put(event.getKeyCode(), false);
  }
  
  public void keyTyped(KeyEvent e) {
    // System.out.print(e.getKeyChar());
  }
  
  public Set<Integer> getKeys() {
    return keys.keySet();
  }
}
