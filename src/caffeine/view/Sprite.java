package caffeine.view;

import java.awt.Graphics2D;
import java.awt.Image;

import caffeine.world.Location;

public class Sprite {
  protected Image sprite;
  private Location loc;
  private static Spritesheet source;

  public Sprite(Location l, int spriteID){
    loc = l;
    sprite = source.sprite(spriteID);
  }

  public Location loc(){
    return loc;
  }

  public void paint(Graphics2D g2){
    g2.drawImage(sprite, loc.x()-15, loc.y()-15, null);
  }

  public String toString(){
    return "Sprite";
  }

  public static void setSource(Spritesheet spriteSheet) {
    source = spriteSheet;
  }

}
