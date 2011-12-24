package caffeine;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Timer{
	static Clock instance = new Clock();
	ArrayList<TimerTask> events = new ArrayList<TimerTask>();
	int delay;

	private Clock(){
		scheduleAtFixedRate(new TimerTask(){
			public void run(){
				tick();
			}}, 0, 1000/60);
	}

	public void add(TimerTask t){
		events.add(t);
	}

	public static Clock getInstance(){
		return instance;
	}

	private void tick(){
		try {
			for(TimerTask t : events){
				t.run();
			}
		}catch (Throwable ex) { }
	}
}
