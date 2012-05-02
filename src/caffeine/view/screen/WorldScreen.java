package caffeine.view.screen;

import java.awt.Graphics;

import caffeine.world.Map;

public class WorldScreen extends Screen {
  private static final long serialVersionUID = 3103847269699914556L;
  private Map map;
  
  public void setMap(Map map) {
    this.map = map;
  }
  
  public void paintComponent(Graphics g) {
    img = camera.view(map);
    super.paintComponent(g);
  }
  
}
