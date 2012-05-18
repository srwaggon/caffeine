package caffeine.view;

import java.awt.Dimension;
import java.awt.Image;

import caffeine.entity.Entity;
import caffeine.world.Loc;
import caffeine.world.Map;

public class Camera {
  protected Dimension dims;
  protected Loc loc = new Loc(0, 0, 0);

  public Camera(Dimension d) {
    dims = d;
  }

  public void focusOn(Loc l) {
    loc = l;
  }

  public void focusOn(Entity e) {
    loc = e.loc();
  }

  public Loc loc() {
    return loc;
  }

  public Image view(Map map) {
    MapView mapview = new MapView(map);
    int w = dims.width;
    int h = dims.height;
    int x = loc.x() - (w / 2);
    int y = loc.y() - (h / 2);

    if (x < 0) {
      x = 0;
    }
    if (x + w >= mapview.getWidth()) {
      x = mapview.getWidth() - w;
    }
    if (y < 0) {
      y = 0;
    }
    if (y + h >= mapview.getHeight()) {
      y = mapview.getHeight() - h;
    }
    return mapview.getSubimage(x, y, w, h);
  }
}
