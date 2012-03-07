package caffeine.entity.instance;

import caffeine.entity.Actor;
import caffeine.entity.brain.instance.PlayerBrain;
import caffeine.view.InputHandler;

public class PlayerEntity extends Actor {
  
  public PlayerEntity(InputHandler input) {
    brain = new PlayerBrain(input);
  }
}
