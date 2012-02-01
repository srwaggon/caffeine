package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;

public class Player extends Actor {

  public Player(Location loc, InteractionHandler interactions){
    super(loc);
    brain = new PlayerBrain(interactions);
    spriteID = 2;
  }
}
