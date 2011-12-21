package caffeine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Camera {
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected Entity focus;
	protected Location location;
	protected double scale;

	public Camera(){
		location = new Location();
		scale = 1.0;
	}

	public Camera(Location loc, double zoom){
		location = loc;
		scale = zoom;
	}

	public void focusOn(Location l){
		location = l;
	}

	public void focusOn(Entity s){
		focus = s;
	}

	public Location loc(){
		return location;
	}

	public void view(Graphics2D g2, ArrayList<Sprite> sprites){
		if(focus != null){
			location = focus.loc();
		}
		for(Sprite sprite: sprites){
			RectangularShape shape = (RectangularShape) sprite.shape();
					// TODO fix magic camera offset
			shape.setFrame(
			(scale*sprite.loc().x - (scale*location.x - 300)),
			(scale*sprite.loc().y - (scale*location.y - 200)),
			(scale*shape.getWidth()),
			(scale*shape.getHeight()));
			g2.setColor(sprite.color());
			g2.fill(shape);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(shape);
		}
	}
}
