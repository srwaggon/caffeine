package caffeine.view.screens;

import java.awt.Dimension;

import javax.swing.JPanel;

import caffeine.view.Camera;

public class Screen extends JPanel {
  private static final long serialVersionUID = -2226504463501471657L;
  protected Camera camera = new Camera(getPreferredSize());
  
  public final Dimension getPreferredSize() {
    return new Dimension(600, 400);
  }
  
  public Camera camera() {
    return camera;
  }
}
