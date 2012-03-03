package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.view.InputHandler;
import caffeine.world.Location;

public class PlayerEntity extends Actor {
  
  public PlayerEntity(Location loc, InputHandler interactions) {
    super(loc);
    brain = new PlayerBrain(interactions);
    spriteID = 2;
  }
}
