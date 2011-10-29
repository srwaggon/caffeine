package caffeine;
import java.util.Timer;

public class Clock extends Timer{
	static Clock instance;
	
	private Clock(){
		super();
	}
	
	public static Clock getInstance(){
		if(instance == null){
			instance = new Clock();
		}
		return instance;
	}
}
