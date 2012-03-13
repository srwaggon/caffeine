package caffeine.view;

import java.awt.Graphics2D;
import java.awt.Image;

public class Sprite {
  private static Spritesheet sheet = new Spritesheet();
  Image img;
  int x, y;
  
  public Sprite(int spriteID, int x, int y) {
    img = Sprite.sheet.get(spriteID);
    this.x = x;
    this.y = y;
  }
  
  public Sprite(int spriteID) {
    img = Sprite.sheet.get(spriteID);
    x = 0;
    y = 0;
  }
  
  public void render(Graphics2D g2, int xOffset, int yOffset) {
    g2.drawImage(img, x - img.getWidth(null) / 2 + xOffset,
        y - img.getHeight(null) / 2 + yOffset, null);
  }
}
