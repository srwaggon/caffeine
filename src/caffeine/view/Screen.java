package caffeine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import caffeine.world.Map;


public class Screen extends JPanel {
  private static final long serialVersionUID = 1941560650859760887L;
  Camera camera;
  Map map;

  public Screen(Map m){
    map = m;
    camera = new Camera(getPreferredSize());
    setBackground(Color.black);
  }

  public Camera camera(){
    return camera;
  }

  public Dimension getPreferredSize(){
    return new Dimension(600, 400);
  }

  public void setCurrentMap(Map map){
    this.map = map;
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Image img = camera.view(map);
    g.drawImage(img, 0, 0, null);
  }

}
