package caffeine.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Spritesheet {
  protected int tileSize = 32;
  public int width, height;
  public int[] pixels;

  protected HashMap<Integer, Image> sprites = new HashMap<Integer, Image>();

  /**
   * Creates a Spritesheet from the file located in the path specified.
   * 
   * @param path
   *          indicating sprite image file location
   */
  public Spritesheet(String path) {
    BufferedImage sheet;
    try {
      sheet = ImageIO.read(getClass().getResourceAsStream(path));
      width = sheet.getWidth();
      height = sheet.getHeight();
      pixels = sheet.getRGB(0, 0, width, height, null, 0, width);
      for(int i = 0; i < pixels.length; i++){
        //pixels[i] = (pixels[i] & 0xff);
        if (i < 33){
          System.out.println(i + " : " + pixels[i]);
        }
      }
    } catch (Throwable ex) {
      ex.printStackTrace();
    }
  }
}
