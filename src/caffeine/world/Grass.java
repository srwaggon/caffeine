package caffeine.world;

import java.awt.*;

public class Grass extends TileObject{
    private Color LIMEGREEN = new Color(50,205,50);
    
    public Grass(){
    	color = LIMEGREEN;
    	drag = 1;
		isBlocked = false;
		isSafe = true;
		symbol = ".";
    }
}