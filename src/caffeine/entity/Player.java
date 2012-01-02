package caffeine.entity;

import caffeine.CaffeineGame;
import caffeine.entity.brain.PlayerBrain;
import caffeine.world.Location;

public class Player extends Actor {

	public Player(){
		this(new Location());
	}
	public Player(Location loc){
		super(loc);
		brain = new PlayerBrain(CaffeineGame.interactionHandler());
	}
}
