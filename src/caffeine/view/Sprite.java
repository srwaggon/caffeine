package caffeine.view;

import java.awt.Color;
import java.awt.Shape;

import caffeine.world.Location;

public class Sprite {
	private Color color;
	private Location loc;
	private Shape shape;
	
	public Sprite(Color c, Location l, Shape s){
		this.color = c;
		this.loc = l;
		this.shape = s;
	}
	
	public Color getColor(){return color;}
	
	public Location getLoc(){return loc;}
	
	public Shape getShape(){return shape;}
	
	public String toString(){
		return "Sprite";
	}

}
