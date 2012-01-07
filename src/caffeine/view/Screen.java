package caffeine.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Screen extends JPanel {
  Camera camera;

  public Screen(){
    camera = new Camera(getPreferredSize());
  }

  public Camera camera(){
    return camera;
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 400);
  }

  public void paintComponent(Graphics g){
    camera.view(g);
  }

}
