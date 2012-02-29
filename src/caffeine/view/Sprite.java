package caffeine.view;

import java.awt.Graphics2D;
import java.awt.Image;

import caffeine.world.Location;

public class Sprite {
  private static Spritesheet sheet = new Spritesheet();
  Image img;
  
  public Sprite(int spriteID) {
    img = Sprite.sheet.get(spriteID);
  }
  
  public void render(Graphics2D g2, Location loc) {
    g2.drawImage(img, loc.x(), loc.y(), null);
  }
}
