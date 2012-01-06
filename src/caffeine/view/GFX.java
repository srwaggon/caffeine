package caffeine.view;

import java.awt.Image;



/**
 * GFX handles all work associated with graphics.
 * @author Fnar
 *
 */
public class GFX {
  protected GUI gui = new GUI();
  public static Spritesheet spritesheet = new Spritesheet("res/sprites.png");
  public static Spritesheet tilesheet = spritesheet;

  public GFX(){
  }

  public Camera camera(){
    return gui.getContentPane().camera();
  }

  public static Image getSprite(int id){
    return spritesheet.get(id);
  }

  public static Image getTile(int id){
    return tilesheet.get(id);
  }

  public void tick(){
    gui.repaint();
  }
}
