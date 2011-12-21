package caffeine.world;
import java.awt.*;

public class Wall extends TileObject{
    public Wall(){
    	color = Color.GRAY;
    	drag = 1.5;
		isBlocked = true;
		isSafe = true;
		symbol = "#";
    }
}