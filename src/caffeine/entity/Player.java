package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.world.World;


public class Player extends Mob{

  public Player(World world){
    super(world);
    brain = new PlayerBrain(this);
  }
}
