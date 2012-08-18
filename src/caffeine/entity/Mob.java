package caffeine.entity;

import caffeine.entity.brain.Brain;
import caffeine.world.World;


public class Mob extends Entity{
  protected boolean isAlive = true;
  protected Brain brain;

  public Mob(World world){
    super(world);
    brain = new Brain(this);
  }

  public void tick(){
    brain.tick();
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void die() {
    isAlive = false;
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

}
