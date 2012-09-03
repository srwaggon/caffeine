package caffeine.entity;

import caffeine.entity.brain.PlayerBrain;
import caffeine.gfx.InputHandler;
import caffeine.world.Map;


public class Player extends Mob{

  public Player(InputHandler input, Map map){
    super(map);
    brain = new PlayerBrain(input, this);
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
