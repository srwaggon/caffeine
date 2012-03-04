package caffeine.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import caffeine.world.Map;

public class MapView {
  private BufferedImage view;
  private Map map;
  private Spritesheet tilesheet;
  
  public MapView(Map m) {
    map = m;
    view = new BufferedImage(map.width(), map.height(),
        BufferedImage.TYPE_INT_ARGB);
    tilesheet = new Spritesheet();
    paintComponent(view.createGraphics());
  }
  
  public MapView(Map m, Spritesheet tilesheet) {
    map = m;
    view = new BufferedImage(map.width(), map.height(),
        BufferedImage.TYPE_INT_ARGB);
    this.tilesheet = tilesheet;
    paintComponent(view.createGraphics());
  }
  
  public int getHeight() {
    return view.getHeight();
  }
  
  public Image getImage() {
    return view;
  }
  
  public Image getSubimage(int x, int y, int w, int h) {
    return view.getSubimage(x, y, w, h);
  }
  
  public int getWidth() {
    return view.getWidth();
  }
  
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    map.renderTiles(g2, tilesheet);
    map.renderEntities(g2, tilesheet);
  }
}
