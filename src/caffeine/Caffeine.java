package caffeine;

import pixl.Renderable;
import pixl.Screen;
import caffeine.gfx.GUI;
import caffeine.world.Map;

public class Caffeine extends Game implements Renderable {
  protected GUI gui = new GUI("Caffeine");
  private final int currentMap = 0;

  public Caffeine() {
    gui.addRenderable(this);
  }

  @Override
  public void render(Screen screen) {
    Map map = getMap(currentMap);
    map.renderBackground(screen);
    map.renderSprites(screen);
  }

  @Override
  public Void call() throws Exception {
    gui.start();
    return super.call();
  }

  public GUI getGUI() {
    return gui;
  }
}
