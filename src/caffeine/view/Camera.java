package caffeine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.world.Location;

public class Camera {
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected int depthOfField;
	protected Entity focus;
	protected Location location;
	
	
	public Camera(){
		depthOfField = 400; // TODO fix this magic number
		location = new Location();
	}
	
	public Camera(Location loc, int depthOfField){
		this.location = loc;
		this.depthOfField = depthOfField;
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
			RectangularShape shape = (RectangularShape) sprite.getShape();
			Rectangle2D newRect = new Rectangle2D.Double(
					sprite.getLoc().x - (location.x - depthOfField/2),
					sprite.getLoc().y - (location.y - depthOfField/2),
					shape.getWidth(),
					shape.getHeight());
			g2.setColor(sprite.getColor());
			g2.fill(newRect);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(newRect);
		}
	}
}
