package caffeine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import caffeine.world.Point;
import caffeine.world.Sprited;

public class Camera {
	Point center;
	int view;
	Sprited focus;
	
	public Camera(int x_offset, int y_offset, int depthOfField){
		this.center = new Point(x_offset, y_offset);
		this.view = depthOfField;
	}
	
	public void focusOn(Point p){
		center = p;
	}
	public void focusOn(Sprited s){
		focus = s;
	}
	
	public void tick(){
		if(focus != null){
			Rectangle r = focus.getSprite().getShape().getBounds();
			center = new Point((int)r.getCenterX(), (int)r.getCenterY());
		}
	}
	
	public void view(Graphics2D g2, ArrayList<Sprite> sprites){
		for(Sprite sprite: sprites){
			RectangularShape shape = (RectangularShape) sprite.getShape();
			Rectangle2D newRect = new Rectangle2D.Double(
					shape.getX() - (center.x - view/2),
					shape.getY() - (center.y - view/2),
					shape.getWidth(),
					shape.getHeight());
			g2.setColor(sprite.getColor());
			g2.fill(newRect);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(newRect);
		}
	}
}
