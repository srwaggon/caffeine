package caffeine.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;

import javax.imageio.ImageIO;


public class Spritesheet{
  public static Spritesheet sprites = new Spritesheet("res/sprites.png");
  private static BufferedImage sheet;
  protected int tileHeight = 32;
  protected int tileWidth = 32;

  public Spritesheet(String path){
    try{
      BufferedImage sht = ImageIO.read(new File(path));
      sheet = ImageIO.read(new File(path));
      //sheet = (BufferedImage)transformBlackToTransparency(sht);
    } catch (Throwable ex){ }

  }

  private static Image transformBlackToTransparency(BufferedImage image){
    ImageFilter filter = new RGBImageFilter(){

      private int black = 0xFF000000;
      private int transparent = 0x00000000;

      public final int filterRGB(int x, int y, int rgb){
        if((rgb | black) == black){
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

  public Image getSprite(int index){
    int tilesPerCol = sheet.getHeight()/tileHeight;
    int tilesPerRow = sheet.getWidth()/tileWidth;
    BufferedImage subimage = sheet.getSubimage(index % tilesPerRow, index / tilesPerCol, tileWidth, tileHeight);
    return transformBlackToTransparency(subimage);
  }


}
