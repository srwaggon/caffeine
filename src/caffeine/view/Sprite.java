package caffeine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import caffeine.util.Util;
import caffeine.world.Location;

public class Sprite {
  private Color color;
  private int r;
  private Location loc;

  public Sprite(Location l, int radius){
    color = Util.randomColor();
    loc = l;
    r = radius;
  }

  public void color(Color c ){color = c;}

  public Color color(){return color;}

  public Location loc(){return loc;}

  public Shape shape(){
    return new RoundRectangle2D.Double(loc.x()-r, loc.y()-r, r*2, r*2, r, r);
  }

  public void paint(Graphics2D g2){
    Image img = Spritesheet.sprites.getSprite(0);
    g2.drawImage(img, loc.x()-15, loc.y()-15, null);

    /*
    Shape s = shape();
    g2.setColor(color);
    g2.fill(s);
    g2.setColor(Color.white);
    g2.draw(s);
     */
  }

  public String toString(){
    return "Sprite";
  }

}
