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
    int w = dims.width;
    int h = dims.height;
    int x = loc.x();
    int y = loc.y();

    Image mapViewImage = mapview.getImage();
    int renderX = w/2 - x;
    int renderY = h/2 - y;

    if(w/2 >= x){
      renderX = 0;
    } else if (mapview.getWidth() <= x + w/2){
      renderX = w - mapview.getWidth();
    }

    if(y <= h/2){
      renderY = 0;
    } else if (mapview.getHeight() <= y + h/2){
      renderY = h - mapview.getHeight();
    }

    g.drawImage(mapViewImage, renderX, renderY, null);
  }
}
