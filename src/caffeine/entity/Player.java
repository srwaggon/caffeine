package caffeine.entity;

import caffeine.Game;
import caffeine.world.Location;

public class Player extends Actor {

	public Player(Location loc){
		super(loc);
		brain = new InteractiveBrain(Game.instance().interactionHandler());
	}
}
