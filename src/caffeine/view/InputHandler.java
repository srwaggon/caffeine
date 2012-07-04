package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Set;

public class InputHandler implements InputListener {
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

  public void mouseClicked(MouseEvent event) {

  }

  public void mouseDragged(MouseEvent event) {

  }

  public void mouseEntered(MouseEvent event) {

  }

  public void mouseExited(MouseEvent event) {

  }

  public void mouseMoved(MouseEvent event) {

  }

  public void mousePressed(MouseEvent event) {

  }

  public void mouseReleased(MouseEvent event) {
    System.out.println(event);
  }
}
