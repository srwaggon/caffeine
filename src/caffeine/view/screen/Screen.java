package caffeine.view.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import caffeine.view.Camera;

public class Screen extends JPanel {
  private static final long serialVersionUID = -2226504463501471657L;
  protected Camera camera = new Camera(getPreferredSize());
  protected Image img = null;
  
  public final Dimension getPreferredSize() {
    return new Dimension(600, 400);
  }
  
  public Camera camera() {
    return camera;
  }
  
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }
}
