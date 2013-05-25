package caffeine;

import pixl.Renderable;
import pixl.Screen;
import caffeine.world.Map;

public class Caffeine extends Game implements Renderable {
  private final int currentMap = 0;
  
  @Override
  public void render(Screen screen) {
    System.out.println("rendering.");
    Map map = getMap(currentMap);
    map.renderBackground(screen);
    map.renderSprites(screen);
  }
}
