package caffeine.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import caffeine.Game;
import caffeine.world.Map;


public class MapView{
  BufferedImage view;
  protected Map map;

  public MapView(Map m) {
    map = m;
    view = new BufferedImage(map.width(), map.height(), BufferedImage.TYPE_INT_ARGB);
    map.paint(view.createGraphics());
  }

  public int getHeight(){
    return view.getHeight();
  }

  public Image getImage(){
    return view;
  }

  public Image getSubimage(int x, int y, int w, int h){
    return view.getSubimage(x, y, w, h);
  }

  public int getWidth(){
    return view.getWidth();
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Game.game().world().paint(g2);
  }
}

