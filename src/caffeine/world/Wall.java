package caffeine.world;
import java.awt.*;

public class Wall extends TileObject{
    public Wall(){
    	color = Color.GRAY;
		isBlocked = true;
		isSafe = true;
		symbol = "#";
    }
}