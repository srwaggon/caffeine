package caffeine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.world.Location;
import caffeine.world.Map;

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
    /* draw all the tiles*/
    Map m = loc.map();
    for(Sprite sprite : m.tileSprites()){
      RectangularShape shape = (RectangularShape) sprite.shape();
      Rectangle2D frame = shape.getFrame();
      double x = scale * (sprite.loc().x() - (loc.x() - dims.width / 2));
      double y = scale * (sprite.loc().y() - (loc.y() - dims.height / 2));
      double w = scale * shape.getWidth();
      double h = scale * shape.getHeight();
      shape.setFrame(x, y, w, h);
      g2.setColor(sprite.color());
      g2.fill(shape);
      g2.setColor(Color.DARK_GRAY);
      g2.draw(new Line2D.Double(x, y, x+w, y));
      g2.draw(new Line2D.Double(x, y, x, y+h));
    }

    /* draw all of the sprites */
    for(Entity e : m.entities()){
      Sprite sprite = e.sprite();
      RectangularShape shape = (RectangularShape) sprite.shape();
      Rectangle2D frame = shape.getFrame();
      double x = scale * (sprite.loc().x() - shape.getWidth() / 2 - (loc.x() - dims.width / 2));
      double y = scale * (sprite.loc().y() - shape.getHeight() / 2 - (loc.y() - dims.height / 2));
      double w = scale * shape.getWidth();
      double h = scale * shape.getHeight();
      shape.setFrame(x, y, w, h);
      g2.setColor(sprite.color());
      g2.fill(shape);
      g2.setColor(Color.white);
      g2.draw(shape);
    }

    g2.dispose();
    sprites = new ArrayList<Sprite>();
  }
}
