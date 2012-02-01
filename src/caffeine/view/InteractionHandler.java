package caffeine.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComponent;

public class InteractionHandler extends JComponent implements KeyListener{
  HashMap<Integer, Boolean> actionCodes = new HashMap<Integer, Boolean>();

  public boolean get(int keyCode){
    if (actionCodes.containsKey(keyCode)){
      return actionCodes.get(keyCode);
    }
    return false;
  }

  @Override
  public void keyPressed(KeyEvent event) {
    actionCodes.put(event.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent event) {
    actionCodes.put(event.getKeyCode(), false);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.err.print(e.getKeyChar());
  }

  public Set<Integer> getKeys(){
    return actionCodes.keySet();
  }
}
