package caffeine.entity;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import caffeine.action.Action;
import caffeine.action.Accelerate;
import caffeine.util.Angle;
import caffeine.view.InteractionHandler;

public class InteractiveBrain extends Brain{
	HashMap<Integer, Action> actionMap;
	InteractionHandler interactions;
	
	public InteractiveBrain(InteractionHandler i){
		interactions = i;
		actionMap = new HashMap<Integer, Action>();
		actionMap.put(KeyEvent.VK_UP, new Accelerate(new Angle(270)));
		actionMap.put(KeyEvent.VK_DOWN, new Accelerate(new Angle(90)));
		actionMap.put(KeyEvent.VK_LEFT, new Accelerate(new Angle(180)));
		actionMap.put(KeyEvent.VK_RIGHT, new Accelerate(new Angle(0)));
	}
	
	@Override
	public ArrayList<Action> next(Actor actor) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		for(int keyCode : actionMap.keySet()){
			if(interactions.get(keyCode))
				actionList.add(actionMap.get(keyCode));
		}
		return actionList;
	}

}
