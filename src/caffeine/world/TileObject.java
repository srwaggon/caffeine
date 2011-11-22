package caffeine.world;
import java.awt.Color;

public abstract class TileObject{
	protected Boolean isBlocked = false;
	protected Boolean isSafe = false;
	protected Color color = Color.BLUE;
	protected double drag = 0.0;
	protected String symbol = "!";
	
	public double drag(){return drag;}
	
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