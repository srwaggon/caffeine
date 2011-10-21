package caffeine.world;
import java.awt.Color;

public interface TileObject{
    boolean isBlocked();
    boolean isSafe();
    Color getColor();
}