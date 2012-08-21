package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.world.World;


public class Player extends Mob{

  public Player(World world){
    super(world);
    brain = new PlayerBrain(this);
  }

  public boolean move(int xa, int ya){
    return super.move(xa, ya);
  }

  public void takeItem(ItemEntity item){
    item.take(this);
  }

  public void tick(){
    super.tick();
  }
}
