package caffeine.world.tile;
import java.awt.Color;

public abstract class TileObject{
	protected Boolean pass = false;
	protected Boolean safe = false;
	protected Color color = Color.BLUE;
	protected double drag = 0.0;
	protected String symbol = "!";

	public double drag(){return drag;}

	public boolean pass(){
		return pass;
	}
	public boolean safe(){
		return safe;
	}
	public Color color(){
		return color;
	}

	public void setRed(){
		color = Color.RED;
	}
	public String toString(){
		return symbol;
	}
}