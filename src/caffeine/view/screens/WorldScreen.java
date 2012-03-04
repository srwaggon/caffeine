package caffeine.view.screens;

import java.awt.Graphics;
import java.awt.Image;

import caffeine.world.Map;

public class WorldScreen extends Screen {
  private static final long serialVersionUID = 3103847269699914556L;
  private Map map;
  
  public void setCurrentMap(Map map) {
    this.map = map;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image img = camera.view(map);
    g.drawImage(img, 0, 0, null);
  }
  
}
