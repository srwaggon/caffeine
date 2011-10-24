package caffeine;
import java.util.*;

public class GameTick extends TimerTask{
	
	public GameTick(){
		super();
	}
	
	public void run(){
		Game g = Game.getInstance();
		g.getCurrentMap().tick();
		g.getGUI().getContentPane().getCamera().centerOn(g.getCurrentMap().getEntities().get(0).getCenter());
		g.getGUI().repaint();
    }
}
