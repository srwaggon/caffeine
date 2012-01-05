package caffeine.view;


/**
 * GFX handles all work associated with graphics.
 * @author Fnar
 *
 */
public class GFX {
  protected GUI gui = new GUI();
  protected Spritesheet spriteSheet = new Spritesheet("res/sprites.png");

  public GFX(){
    spriteSheet = new Spritesheet("res/sprites.png");
    Sprite.setSource(spriteSheet);
  }

  public Camera camera(){
    return gui.getContentPane().camera();
  }

  public Spritesheet spriteSheet(){
    return spriteSheet;
  }

  public void tick(){
    gui.repaint();
  }
}
