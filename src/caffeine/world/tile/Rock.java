package caffeine.world.tile;
import java.awt.Color;


public class Rock extends TileObject{
	public Rock(){
		color = Color.GRAY;
		drag = 1.5;
		pass = false;
		safe = true;
		symbol = "#";
	}
}