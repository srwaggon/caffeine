package caffeine.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Spritesheet {
  protected int tileSize = 32;
  public int width, height;
  public int[] pixels;


  public Spritesheet(String path) {
    BufferedImage sheet;
    try {
      sheet = ImageIO.read(getClass().getResourceAsStream(path));
      width = sheet.getWidth();
      height = sheet.getHeight();
      pixels = sheet.getRGB(0, 0, width, height, null, 0, width);
    } catch (Throwable ex) {
      ex.printStackTrace();
    }
  }
}
