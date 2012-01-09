package caffeine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Screen extends JPanel {
  Camera camera;

  public Screen(){
    camera = new Camera(getPreferredSize());
    setBackground(Color.black);
  }

  public Camera camera(){
    return camera;
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 400);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    camera.view(g);
  }

}
