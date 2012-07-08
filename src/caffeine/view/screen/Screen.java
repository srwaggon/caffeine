package caffeine.view.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import caffeine.view.Spritesheet;

public class Screen extends Canvas {
  private static final long serialVersionUID = -2226504463501471657L;
  protected Spritesheet sprites = new Spritesheet("sprites.png");
  int xOffset = 0, yOffset = 0;
  int WIDTH = 400;
  int HEIGHT = WIDTH * 10 / 16;

  public final Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void render(int spriteID, int x, int y){
    BufferStrategy bs = getBufferStrategy();
    if(bs == null){
      createBufferStrategy(1);
      return;
    }

    if(xOffset <= x && x < WIDTH + xOffset && yOffset <= y && y < HEIGHT + yOffset){
      Graphics gfx = bs.getDrawGraphics();
      gfx.drawImage(sprites.get(spriteID), x-xOffset, y-yOffset, null);
      gfx.dispose();
      bs.show();
    }
  }
}
