package caffeine.world;
import java.awt.*;

public class Wall extends TileObject{
    public boolean isBlocked(){
        return true;
    }
    public boolean isSafe(){
        return true;
    }
    public Color getColor(){
        return Color.GRAY;
    }
    public String toString(){
        return "#";
    }
}