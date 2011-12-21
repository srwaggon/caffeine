package caffeine.view;

import java.awt.Color;
import java.awt.Shape;

import caffeine.world.Location;

public class Sprite {
	private Color color;
	private Location loc;
	private Shape shape;

	public Sprite(Color c, Location l, Shape s){
		color = c;
		loc = l;
		shape = s;
	}

	public void color(Color c ){color = c;}
	public Color color(){return color;}

	public Location loc(){return loc;}

	public Shape shape(){return shape;}

	public String toString(){
		return "Sprite";
	}

}
