package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComponent;

import caffeine.Game;

@SuppressWarnings("serial")
public class InputHandler extends JComponent implements KeyListener,
    MouseListener {
  protected final Game game;
  HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
  
  public InputHandler(Game game) {
    this.game = game;
  }
  
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
