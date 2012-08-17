package caffeine.gfx;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface InputListener extends MouseMotionListener, KeyListener,
    MouseListener {

  public abstract void keyPressed(KeyEvent ke);

  public abstract void keyReleased(KeyEvent ke);

  public abstract void keyTyped(KeyEvent ke);

  public abstract void mouseClicked(MouseEvent me);

  public abstract void mouseDragged(MouseEvent me);

  public abstract void mouseEntered(MouseEvent me);

  public abstract void mouseExited(MouseEvent me);

  public abstract void mouseMoved(MouseEvent me);

  public abstract void mousePressed(MouseEvent me);

  public abstract void mouseReleased(MouseEvent me);
}
