package caffeine.entity;

import java.util.List;

import caffeine.entity.brain.Brain;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.World;


public class Mob extends Entity{
  protected boolean isAlive = true;
  protected Brain brain;
  protected int health = 10;
  protected int power = 1;

  public Mob(World world){
    super(world);
    brain = new Brain(this);
  }

  public void tick(){
    brain.tick();
  }

  public void attack(){
    int range = 20;
    // use this entity's range to hurt the entities within a given proximity
    if (dir == Dir.UP)  hurt(loc.x - range/2, loc.y - range, loc.x + range/2, loc.y);
    if (dir == Dir.DOWN) hurt(loc.x - range/2, loc.y, loc.x + range/2, loc.y + range);
    if (dir == Dir.LEFT) hurt(loc.x - range, loc.y - range/2, loc.x, loc.y + range/2);
    if (dir == Dir.RIGHT) hurt(loc.x, loc.y - range/2, loc.x + range, loc.y + range/2);
  }

  public void takeDamage(int dmg){
    health -= dmg;
    if (health <= 0) die();
    Sound.HURT.play();
    System.out.println("heatlh remaining: " + health);
  }

  public void takeItem(ItemEntity item){
    item.take(this);
    Sound.ITEM.play();
  }

  public void hurt(int x0, int y0, int x1, int y1){
    List<Entity> entities = getMap().getEntities(x0, y0, x1, y1);
    for (Entity e : entities)
      if (!e.equals(this)) e.takeDamage(power);
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void die() {
    isAlive = false;
    remove();
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

}
