package caffeine.world;
import java.awt.Color;

public abstract class TileObject{
    public boolean isBlocked(){
    	return false;
    }
    public boolean isSafe(){
    	return true;
    }
    public Color getColor(){
    	return Color.BLACK;
    }
    public String toString(){
    	return "TileObject";
    }
}