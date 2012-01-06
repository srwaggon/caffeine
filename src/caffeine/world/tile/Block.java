package caffeine.world.tile;
import java.awt.Color;


public class Block extends TileType{
	public Block(){
		color = Color.GRAY;
		drag = 1.5;
		pass = false;
		safe = true;
		symbol = "#";
	}
}