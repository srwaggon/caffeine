package caffeine.view.screen;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuScreen extends Screen {
  private static final long serialVersionUID = 7919551422870447286L;
  
  public MenuScreen() {
    try {
      img = ImageIO.read(new File("res/space_1.png"));
    } catch (IOException e) {
      System.exit(-1);
    }
  }
}
