package caffeine.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Camera {
  protected Dimension dims;
  protected Location loc;

  public Camera(Dimension d){
    dims = d;
    loc = new Location();
  }

  public void focusOn(Location l){
    loc = l;
  }

  public void focusOn(Entity e){
    loc = e.loc();
  }

  public Location loc(){
    return loc;
  }

  public void view(Graphics g){
    MapView mapview = new MapView(loc.map());
    int w = dims.width  > mapview.getWidth()  ? mapview.getWidth()  : dims.width;
    int h = dims.height > mapview.getHeight() ? mapview.getHeight() : dims.height;
    int x = loc.x() - w / 2;
    int y = loc.y() - h / 2;

    if (x < 0) {
      x = 0;
    }
    if (y < 0) {
      y = 0;
    }

    Image mapViewImage = mapview.getImage();
    int renderX = w/2 - loc.x();
    int renderY = h/2 - loc.y();
    g.drawImage(mapViewImage, renderX, renderY, null);
  }
}
