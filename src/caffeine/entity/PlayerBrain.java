package caffeine.entity;

import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.view.InteractionHandler;
import caffeine.world.Direction;

public class PlayerBrain extends Brain{
	InteractionHandler interactions;
	
	public PlayerBrain(InteractionHandler i){
		interactions = i;
	}
	
	@Override
	public Action next() {
		if(interactions.get("up") && !interactions.get("down")){
			return new Move(Direction.NORTH);
		}else if (interactions.get("down") && !interactions.get("up")){
			return new Move(Direction.SOUTH);
		}
		if(interactions.get("left") && !interactions.get("right")){
			return new Move(Direction.WEST);			
		}else if (interactions.get("right") && !interactions.get("left")){
			return new Move(Direction.EAST);
		}
		return Action.Inaction;
	}

}
