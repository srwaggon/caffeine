package caffeine.view;

import java.awt.Color;
import java.awt.Shape;

import caffeine.world.Location;

public class Sprite {
	private Color color;
	private Location location;
	private Shape shape;
	
	public Sprite(Color color, Location loc, Shape shape){
		this.color = color;
		this.location = loc;
		this.shape = shape;
	}
	
	public Color getColor(){return color;}
	
	public Location getLoc(){return location;}
	
	public Shape getShape(){return shape;}
	
	public String toString(){
		return "Sprite";
	}

}
