package caffeine.view;

import java.awt.Image;

import caffeine.Server;
/**
 * GFX handles all work associated with graphics.
 * @author Fnar
 *
 */
public class GFX {
  protected GUI gui;
  private static Spritesheet spritesheet = new Spritesheet("res/sprites.png");
  private static Spritesheet tilesheet = spritesheet;

  public GFX(){
    gui = new GUI(Server.interactionHandler());
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
