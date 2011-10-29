package caffeine.entity;

import caffeine.Game;
import caffeine.world.Location;

public class Player extends Entity {

	public Player(Location loc){
		super(loc);
		brain = new InteractiveBrain(Game.instance().interactionHandler());
	}
}
