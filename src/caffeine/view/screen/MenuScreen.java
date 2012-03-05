package caffeine.view.screen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuScreen extends Screen {
  private static final long serialVersionUID = 7919551422870447286L;
  
  public void paintComponent(Graphics g) {
    
    Image menuImg;
    try {
      menuImg = ImageIO.read(new File("res/space_1.png"));
      g.drawImage(menuImg, 0, 0, null);
    } catch (IOException e) {
      System.exit(-1);
    }
  }
}
