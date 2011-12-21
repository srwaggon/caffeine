package caffeine;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Timer{
	static Clock instance;
	ArrayList<TimerTask> events = new ArrayList<TimerTask>();
	int delay;

	private Clock(){
		scheduleAtFixedRate(new TimerTask(){
			public void run() {
				tick();
			}}, 0, 100);
	}

	public void add(TimerTask t){
		events.add(t);
	}

	public static Clock getInstance(){
		if(instance == null){
			instance = new Clock();
		}
		return instance;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	private void tick(){
		try {
			for(TimerTask t : events){
				t.run();
			}
			if (delay > 0) {
				Thread.sleep(delay);
			}
		}
		catch (Throwable ex) { }
	}
}
