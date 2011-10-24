package caffeine.view;

import java.awt.Color;
import java.awt.Shape;

public class Sprite {
	private Color color;
	private Shape shape;
	
	public Sprite(Color color, Shape shape){
		this.color = color;
		this.shape = shape;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Shape getShape(){
		return shape;
	}

}
