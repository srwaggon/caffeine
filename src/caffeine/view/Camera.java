package caffeine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Camera {
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
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

	public void view(Graphics2D g2){
		sprites.addAll(loc.map().sprites());
		for(Sprite sprite: sprites){
			RectangularShape shape = (RectangularShape) sprite.shape();
			shape.setFrame(
					(scale*(sprite.loc().x - (loc.x - dims.width/2))),
					(scale*(sprite.loc().y - (loc.y - dims.height/2))),
					(scale*shape.getWidth()),
					(scale*shape.getHeight()));
			g2.setColor(sprite.color());
			g2.fill(shape);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(shape);

		}
		g2.dispose();
		sprites = new ArrayList<Sprite>();
	}
}
