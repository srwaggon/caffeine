package caffeine.entity.brain;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import caffeine.action.Action;
import caffeine.action.Report;
import caffeine.entity.Actor;
import caffeine.view.InteractionHandler;

public class InteractiveBrain extends Brain{
	HashMap<Integer, Action> actionMap = new HashMap<Integer, Action>();
	InteractionHandler interactions;

	public InteractiveBrain(InteractionHandler ih){
		interactions = ih;
		actionMap.put(KeyEvent.VK_UP, new Report("Up pressed"));
		actionMap.put(KeyEvent.VK_DOWN, new Report("Down pressed"));
		actionMap.put(KeyEvent.VK_LEFT, new Report("Left pressed"));
		actionMap.put(KeyEvent.VK_RIGHT, new Report("Right pressed"));
	}

	@Override
	public ArrayList<Action> next(Actor actor) {
		ArrayList<Action> actionList = new ArrayList<Action>();
		for(int keyCode : actionMap.keySet()){
			if(interactions.get(keyCode)) {
				actionList.add(actionMap.get(keyCode));
			}
		}
		return actionList;
	}

}
