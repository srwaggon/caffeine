package caffeine.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Camera {
  protected List<Sprite> sprites = new ArrayList<Sprite>();
  protected Dimension dims;
  protected Location loc;
  protected double scale;

  public Camera(Dimension d){
    dims = d;
    loc = new Location();
    scale = 1.0;
  }

  public Camera(Location l, double zoom){
    loc = l;
    scale = zoom;
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

  public void render(Graphics2D g2){

  }
}
