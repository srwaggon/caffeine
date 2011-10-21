package caffeine.world;
import java.awt.*;

public class Water implements TileObject{
    public boolean isBlocked(){
        return false;
    }
    public boolean isSafe(){
        return false;
    }
    public Color getColor(){
        return Color.BLUE;
    }
    public String toString(){
        return "~";
    }
}