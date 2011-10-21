package caffeine;
import java.util.*;

public class GameTick extends TimerTask{
	
	public GameTick(){
		super();
	}
	
	public void run(){
		Game.getInstance().getCurrentMap().tick();
		Game.getInstance().getGUI().repaint();
    }
}
