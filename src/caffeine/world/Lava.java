package caffeine.world;
import java.awt.*;

public class Lava extends TileObject{
	public Lava(){
		color = Color.RED;
		isBlocked = false;
		isSafe = false;
		symbol = "!";
	}
}