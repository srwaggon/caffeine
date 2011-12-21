package caffeine.world;
import java.awt.*;

public class Ice extends TileObject{
	public Ice(){
		color = Color.CYAN;
		drag = .5;
		isBlocked = false;
		isSafe = true;
		symbol = "-";
	}
}