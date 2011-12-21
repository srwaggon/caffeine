package caffeine.world.tile;
import java.awt.Color;


public class Ice extends TileObject{
	public Ice(){
		color = Color.CYAN;
		drag = .5;
		pass = true;
		safe = true;
		symbol = "-";
	}
}