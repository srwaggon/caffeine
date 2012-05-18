package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("serial")
public class InputHandler implements KeyListener, MouseListener {
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
  
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
}
