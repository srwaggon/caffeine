package caffeine.entity;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.view.InteractionHandler;
import caffeine.world.Direction;

public class InteractiveBrain extends Brain{
	HashMap<Integer, Action> actionMap;
	InteractionHandler interactions;
	
	public InteractiveBrain(InteractionHandler i){
		interactions = i;
		actionMap = new HashMap<Integer, Action>();
		actionMap.put(KeyEvent.VK_UP, new Move(new Direction(270)));
		actionMap.put(KeyEvent.VK_DOWN, new Move(new Direction(90)));
		actionMap.put(KeyEvent.VK_LEFT, new Move(new Direction(180)));
		actionMap.put(KeyEvent.VK_RIGHT, new Move(new Direction(0)));
	}
	
	@Override
	public ArrayList<Action> next(Entity e) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		for(int keyCode : actionMap.keySet()){
			if(interactions.get(keyCode))
				actionList.add(actionMap.get(keyCode));
		}
		return actionList;
	}

}
