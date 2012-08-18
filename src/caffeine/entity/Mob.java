package caffeine.entity;

import java.util.List;

import caffeine.entity.brain.Brain;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.World;


public class Mob extends Entity{

  protected boolean flip = false;

  protected boolean isAlive = true;
  protected Brain brain;

  protected int health = 10;
  protected int power = 1;

  protected int za = 0;

  public Mob(World world){
    super(world);
    brain = new Brain(this);
  }

  public void tick(){
    brain.tick();

    flip = !flip;
    if (loc.z >= 0 && flip){
      loc.z += za--;
      if(loc.z <= 0){
        loc.z = 0;
        za = 0;
      }
    }
  }

  public void attack(){
    int range = 8;
    // use this entity's range to hurt the entities within a given proximity
    if (dir == Dir.UP)  hurt(loc.x - range/2, loc.y - range, loc.x + range/2, loc.y - yr);
    if (dir == Dir.DOWN) hurt(loc.x - range/2, loc.y + yr, loc.x + range/2, loc.y + range);
    if (dir == Dir.LEFT) hurt(loc.x - range, loc.y - range/2, loc.x - xr, loc.y + range/2);
    if (dir == Dir.RIGHT) hurt(loc.x + xr, loc.y - range/2, loc.x + range, loc.y + range/2);

    int random = (int)(Math.random() * 4);
    if(random == 0) Sound.SWORD1.play();
    if(random == 1) Sound.SWORD2.play();
    if(random == 2) Sound.SWORD3.play();
    if(random == 3) Sound.SWORD4.play();
  }

  public void hurt(int x0, int y0, int x1, int y1){
    List<Entity> entities = getMap().getEntities(x0, y0, x1, y1);
    for (Entity e : entities)
      if (!e.equals(this)) e.takeDamage(power);
  }

  public void jump(){
    if(loc.z == 0){
      za = 6;
      Sound.JUMP.play();
    }
  }

  public void takeDamage(int dmg){
    health -= dmg;
    Sound.HURT.play();
    if (health <= 0) die();
  }

  public void takeItem(ItemEntity item){
    item.take(this);
    Sound.ITEM.play();
  }

  public boolean touchedBy(Entity e){
    return false;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void die() {
    isAlive = false;
    Sound.ENEMY_DIE.play();
    remove();
  }

  public Brain getBrain() {
    return brain;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

}
