package caffeine.world.tile;
import java.awt.Color;


public class Damage extends TileType{
	public Damage(){
		color = Color.RED;
		pass = true;
		safe = false;
		symbol = "!";
	}
}