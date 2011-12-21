package caffeine.world.tile;

import java.awt.Color;


public class Grass extends TileObject{
	private Color LIMEGREEN = new Color(50,205,50);

	public Grass(){
		color = LIMEGREEN;
		drag = 1;
		pass = true;
		safe = true;
		symbol = ".";
	}
}