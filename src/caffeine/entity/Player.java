package caffeine.entity;

import caffeine.Game;
import caffeine.entity.brain.MoveBrain;
import caffeine.world.Location;

public class Player extends Actor {

	public Player(){
		super();
		brain = new MoveBrain(Game.instance().interactionHandler());
	}
	public Player(Location loc){
		super(loc);
		brain = new MoveBrain(Game.instance().interactionHandler());
	}
}
