package caffeine.view;

import java.util.HashMap;

public class InteractionHandler {
	HashMap<Integer, Boolean> actionCodes = new HashMap<Integer, Boolean>();

	public void set(int keyCode, boolean b){
		actionCodes.put(keyCode, b);
	}

	public boolean get(int keyCode){
		if (actionCodes.containsKey(keyCode)){
			return actionCodes.get(keyCode);
		}
		return false;
	}

}
