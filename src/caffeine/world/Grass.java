package caffeine.world;

import java.awt.*;

public class Grass extends TileObject{
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