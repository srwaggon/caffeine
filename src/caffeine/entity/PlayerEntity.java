package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.view.InputHandler;

public class PlayerEntity extends Actor {
  
  public PlayerEntity(InputHandler input) {
    brain = new PlayerBrain(input);
  }
}
