package caffeine.world;

import java.awt.*;

public class Grass implements TileObject{
    private Color LIMEGREEN = new Color(50,205,50);

    public boolean isBlocked(){
        return false;
    }
    public boolean isSafe(){
        return true;
    }
    public Color getColor(){
        return LIMEGREEN;
    }
    public String toString(){
        return ".";
    }
}