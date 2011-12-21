package caffeine.world.tile;
import java.awt.Color;


public class Lava extends TileObject{
	public Lava(){
		color = Color.RED;
		pass = true;
		safe = false;
		symbol = "!";
	}
}