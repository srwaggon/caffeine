package caffeine.world;
import java.awt.Color;

public abstract class TileObject{
	Color color = Color.BLUE;
	Boolean isBlocked = false;
	Boolean isSafe = false;
	String symbol = "!";
	
    public boolean isBlocked(){
    	return isBlocked;
    }
    public boolean isSafe(){
    	return isSafe;
    }
    public Color getColor(){
    	return color;
    }
    
    public void setRed(){
    	color = Color.RED;
    }
    public String toString(){
    	return symbol;
    }
}