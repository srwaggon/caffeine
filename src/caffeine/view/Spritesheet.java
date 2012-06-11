package caffeine.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.util.HashMap;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * The Spritesheet class handles reading in an image file of sprites (currently
 * only 32x32) and allows for retrieving an individual one, with pure black
 * (0x00000000) set to transparency.
 * 
 * @author Fnar
 * 
 */
public class Spritesheet {
  private BufferedImage sheet;
  protected int tileSize = 32;
  private Dimension dim;
  protected HashMap<Integer, Image> sprites = new HashMap<Integer, Image>();
  protected static String defaultSpritePath = "sprites.png";
  protected static String eclipseDefaultSpritePath = "res/sprites.png";

  public Spritesheet() {
    //this(Spritesheet.eclipseDefaultSpritePath);
    this(Spritesheet.defaultSpritePath);
  }

  /**
   * Creates a Spritesheet from the file located in the path provided.
   * 
   * @param path
   *          indicating sprite image file location
   */
  public Spritesheet(String path) {
    try {
      URL url = getClass().getResource(path);
      sheet = ImageIO.read(url);
      dim = new Dimension(sheet.getWidth(), sheet.getHeight());
    } catch (Throwable ex) {
      ex.printStackTrace();
    }

  }

  /**
   * Used to change the size in pixels of each sprite tile in the spritesheet.
   * 
   * @param size
   *          in pixels for sprite tiles to be
   */
  public void setTileSizeTo(int size) {
    tileSize = size;
  }

  /**
   * Checks each pixel for blackness (0x00000000) and converts it to full alpha
   * transparency (0xFF000000).
   * 
   * @param image
   * @return
   */
  private static Image transformBlackToTransparency(BufferedImage image) {
    ImageFilter filter = new RGBImageFilter() {

      private int black = 0xFF000000;
      private int transparent = 0x00000000;

      public final int filterRGB(int x, int y, int rgb) {
        if ((rgb | black) == black) {
          return transparent;
        } else {
          // no change to make
          return rgb;
        }
      }
    };

    // make a new picture based on the one we've received.
    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
    return Toolkit.getDefaultToolkit().createImage(ip);
  }

  /**
   * Retrieves a tile from the indicated spritesheet using the default tile
   * size.
   * 
   * @param index
   *          of sprite from spritesheet
   * @return image representing the sprite at the requested index.
   */
  public Image get(int index) {
    if (sprites.containsKey(index)) {
      return sprites.get(index);
    } else {
      Image sprite = get(index, tileSize);
      sprites.put(index, sprite);
      return sprite;
    }
  }

  /**
   * Retrieves a tile from the indicated spritesheet using the provided tile
   * size.
   * 
   * @param index
   *          of sprite from spritesheet
   * @return image representing the sprite at the requested index.
   */
  public Image get(int index, int tileSize) {
    int tilesPerCol = dim.height / tileSize;
    int tilesPerRow = dim.width / tileSize;
    int x = index % tilesPerRow * tileSize;
    int y = index / tilesPerCol * tileSize;

    BufferedImage subimage = sheet.getSubimage(x, y, tileSize, tileSize);
    Image sprite = Spritesheet.transformBlackToTransparency(subimage);

    return sprite;
  }
}
