package caffeine.net;

import java.awt.event.KeyEvent;

import caffeine.gfx.InputHandler;

public class NetworkInputHandler extends InputHandler {
  Connection connex;
  
  public NetworkInputHandler(Connection c) {
    connex = c;
  }
  
  public void keyPressed(KeyEvent ke) {
    connex.send("pressed " + ke.getKeyCode());
  }
  
  public void keyReleased(KeyEvent ke) {
    connex.send("released " + ke.getKeyCode());
  }
  
  public void keyTyped(KeyEvent ke) {
    
  }
  
}
