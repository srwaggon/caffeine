package caffeine.world.tile;

import java.awt.Color;


public class Safe extends TileType{
	private Color LIMEGREEN = new Color(50,205,50);

	public Safe(){
		color = LIMEGREEN;
		drag = 1;
		pass = true;
		safe = true;
		symbol = ".";
	}
}