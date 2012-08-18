package caffeine.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Screen extends Canvas {

  private static final long serialVersionUID = -2226504463501471657L;
  final int WIDTH = 600;
  final int HEIGHT = WIDTH * 10 / 16;
  protected int xOffset = 0, yOffset = 0;
  final int PUREBLACK = -16777216;

  private final BufferedImage screen = new BufferedImage(WIDTH, HEIGHT,
      BufferedImage.TYPE_INT_RGB);
  private final int[] pixels = ((DataBufferInt) screen.getRaster()
      .getDataBuffer()).getData();
  protected Spritesheet sheet = new Spritesheet("sprites.png", 32);


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

    int spriteX = spriteID % sheet.PER_ROW;
    int spriteY = spriteID / sheet.PER_ROW;
    int spriteOffset = spriteX * sheet.TILESIZE + spriteY * sheet.TILESIZE * sheet.width;

    for (int row = 0; row < sheet.TILESIZE; row++) {
      if (y + row < 0 || y + row >= HEIGHT)
        continue;

      for (int col = 0; col < sheet.TILESIZE; col++) {
        if (x + col < 0 || x + col >= WIDTH)
          continue;

        int sheetIndex = col + row * sheet.width + spriteOffset;
        int canvasIndex = x + col + (y + row) * WIDTH;

        int colour = sheet.pixels[sheetIndex];
        if(colour != PUREBLACK)
          pixels[canvasIndex] = colour;

      }
    }
  }
}
