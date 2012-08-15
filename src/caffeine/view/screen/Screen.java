package caffeine.view.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import caffeine.view.Spritesheet;

public class Screen extends Canvas {

  private static final long serialVersionUID = -2226504463501471657L;
  protected Spritesheet sprites = new Spritesheet("sprites.png");
  int xOffset = 0, yOffset = 0;
  final int WIDTH = 600;
  final int HEIGHT = WIDTH * 10 / 16;
  private final BufferedImage screen = new BufferedImage(WIDTH, HEIGHT,
      BufferedImage.TYPE_INT_RGB);
  private final int[] pixels = ((DataBufferInt) screen.getRaster()
      .getDataBuffer()).getData();
  int ticks = 0;

  public final Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(1);
      return;
    }
    Graphics gfx = bs.getDrawGraphics();
    gfx.drawImage(screen, 0, 0, null);
    gfx.dispose();
    bs.show();
  }

  public void render(int spriteID, int x, int y) {
    x -= xOffset;
    y -= yOffset;

    int spriteX = spriteID % 32;
    int spriteY = spriteID / 32;
    int spriteOffset = spriteX * 32 + spriteY * 32 * sprites.width;

    for (int row = 0; row < 32; row++) {
      if (y + row < 0 || y + row >= HEIGHT) {
        continue;
      }

      for (int col = 0; col < 32; col++) {
        if (x + col < 0 || x + col >= WIDTH) {
          continue;
        }

        int sheet = col + row * sprites.width + spriteOffset;
        int canvas = (x + col + (y + row) * WIDTH);

        int colour = sprites.pixels[sheet];
        if(colour != -16777216){
          pixels[canvas] = colour;
        }

      }
    }
  }
}
