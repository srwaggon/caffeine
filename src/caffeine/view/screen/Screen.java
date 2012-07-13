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
  private BufferedImage screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
  int ticks = 0;


  public final Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void render(){
    BufferStrategy bs = getBufferStrategy();
    if(bs == null){
      createBufferStrategy(1);
      return;
    }
    Graphics gfx = bs.getDrawGraphics();
    gfx.drawImage(screen, 0, 0, null);
    gfx.dispose();
    bs.show();
  }

  public void render(int spriteID, int x, int y){
    x -= xOffset;
    y -= yOffset;
    for (int i = 0; i < 32 * 32; i++) {
      int spritex = i % sprites.width;
      int spritey = i / sprites.width;
      int screenx = i % WIDTH;
      int screeny = i / WIDTH;

      pixels[(screeny + (WIDTH - sprites.width)) * WIDTH + screenx] = sprites.pixels[spritex + spritey *sprites.width];
    }
  }
}
