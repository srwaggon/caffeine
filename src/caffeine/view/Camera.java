package caffeine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import caffeine.world.Location;

public class Camera {
	Location location;
	int depthOfField;
	Sprited focus;
	
	public Camera(){
		location = new Location();
		depthOfField = 400;
	}
	
	public Camera(Location loc, int depthOfField){
		this.location = loc;
		this.depthOfField = depthOfField;
	}
	
	public void focusOn(Location l){
		location = l;
	}
	
	public void focusOn(Sprited s){
		focus = s;
	}
	
	public void tick(){
		if(focus != null){
			Rectangle r = focus.getSprite().getShape().getBounds();
			location = new Location(
					focus.getLoc().getMapID(),
					((int)r.getCenterX()),
					((int)r.getCenterY()));
		}
	}
	
	public void view(Graphics2D g2, ArrayList<Sprite> sprites){
		for(Sprite sprite: sprites){
			RectangularShape shape = (RectangularShape) sprite.getShape();
			Rectangle2D newRect = new Rectangle2D.Double(
					shape.getX() - (location.getX() - depthOfField/2),
					shape.getY() - (location.getY() - depthOfField/2),
					shape.getWidth(),
					shape.getHeight());
			g2.setColor(sprite.getColor());
			g2.fill(newRect);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(newRect);
		}
	}
}
