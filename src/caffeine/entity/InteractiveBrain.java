package caffeine.entity;

import java.awt.event.KeyEvent;
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
		actionMap.put(KeyEvent.VK_UP, new Move(Direction.NORTH));
		actionMap.put(KeyEvent.VK_DOWN, new Move(Direction.SOUTH));
		actionMap.put(KeyEvent.VK_LEFT, new Move(Direction.WEST));
		actionMap.put(KeyEvent.VK_RIGHT, new Move(Direction.EAST));
	}
	
	@Override
	public Action next(Entity e) {
		for(int keyCode : actionMap.keySet()){
			if(interactions.get(keyCode))
				return actionMap.get(keyCode);
		}
		return Action.Inaction;
	}

}
