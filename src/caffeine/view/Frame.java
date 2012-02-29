package caffeine.view;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Frame {
  List<Sprite> sprites = new ArrayList<Sprite>();
  int x, y;
  
  Frame(List<Sprite> sprites, int x, int y) {
    this.sprites = sprites;
    this.x = x;
    this.y = y;
  }
  
  Frame(Sprite s, int x, int y) {
    sprites.add(s);
    this.x = x;
    this.y = y;
  }
  
  public void render(Graphics2D g2, int xOffset, int yOffset) {
    for (Sprite s : sprites) {
      s.render(g2, x + xOffset, y + yOffset);
    }
  }
}
