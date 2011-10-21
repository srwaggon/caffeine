package caffeine.view;

import java.util.HashMap;

public class InteractionHandler {
	HashMap<String, Boolean> stateMap = new HashMap<String, Boolean>();
	
	public InteractionHandler(){
		stateMap.put("down", false);
		stateMap.put("left", false);
		stateMap.put("right", false);
		stateMap.put("up", false);
		stateMap.put("attack", false);
	}
	
	public void set(String s, boolean b){
		stateMap.put(s, b);
	}
	public boolean get(String s){
		return stateMap.get(s);
	}
}
