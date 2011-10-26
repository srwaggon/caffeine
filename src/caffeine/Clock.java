package caffeine;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Timer{
	static Clock instance;
	
	private Clock(){
		super();
		scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				Game.getInstance().tick();
			}
			
		}, 0, 100);
	}
	
	public static Clock getInstance(){
		if(instance == null){
			instance = new Clock();
		}
		return instance;
	}
}
