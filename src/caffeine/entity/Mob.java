package caffeine.entity;

import java.util.ArrayList;
import java.util.List;

import caffeine.entity.brain.Brain;
import caffeine.items.Heart;
import caffeine.items.Item;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.World;
import caffeine.world.tile.Tile;

public class Mob extends Entity {

  protected boolean flip = false;
  protected boolean isAlive = true;
  protected int hp = 3;
  protected int hurtTime;
  protected int power = 1;
  protected int xKnockback, yKnockback; 
  protected int za = 0;

  // Object Fields
  protected Brain brain;
  protected Item leftHand;
  protected Item rightHand;
  protected List<Item> inventory = new ArrayList<Item>();
  

  public Mob(World world) {
    super(world);
    brain = new Brain(this);
    xr = 4;
    yr = 4;
  }

  public void tick() {
    brain.tick();

    flip = !flip;
    if (loc.z >= 0 && flip) {
      loc.z += za--;
      if (loc.z <= 0) {
        loc.z = 0;
        za = 0;
      }
    }
    if (hurtTime > 0) hurtTime--;

    if (hp <= 0) die();
  }

  public boolean move(int xa, int ya){
    if (xKnockback > 0) {
      xKnockback--;
      move2(-1, 0);
    }
    if (xKnockback < 0) {
      xKnockback++;
      move2(1, 0);
    }
    if (yKnockback > 0) {
      yKnockback--;
      move2(0, -1);
    }
    if (yKnockback < 0) {
      yKnockback++;
      move2(0, 1);
    }
    if (hurtTime > 0) return true;

    return super.move(xa, ya);
  }

  public void attack() {
    int range = 8;
    // use this entity's range to hurt the entities within a given proximity
    if (dir == Dir.UP)
      hurt(loc.x - range / 2, loc.y - range, loc.x + range / 2, loc.y - yr);
    if (dir == Dir.DOWN)
      hurt(loc.x - range / 2, loc.y + yr, loc.x + range / 2, loc.y + range);
    if (dir == Dir.LEFT)
      hurt(loc.x - range, loc.y - range / 2, loc.x - xr, loc.y + range / 2);
    if (dir == Dir.RIGHT)
      hurt(loc.x + xr, loc.y - range / 2, loc.x + range, loc.y + range / 2);

    int random = (int) (Math.random() * 4);
    if (random == 0)
      Sound.SWORD1.play();
    if (random == 1)
      Sound.SWORD2.play();
    if (random == 2)
      Sound.SWORD3.play();
    if (random == 3)
      Sound.SWORD4.play();
  }

  public void heal(int n){
    hp += n;
  }

  public void hurt(int x0, int y0, int x1, int y1) {
    List<Entity> entities = getMap().getEntities(x0, y0, x1, y1);
    for (Entity e : entities)
      if (!e.equals(this))
        e.takeDamage(power, dir);
  }
  
  public void interact(Tile tile, Item item) {
    tile.interact(this, item, this.dir);
  }

  public void jump() {
    if (loc.z == 0) {
      za = 6;
      Sound.JUMP.play();
    }
  }


  public void takeDamage(int dmg, Dir dir) {
    hp -= dmg;
    Sound.HURT.play();

    if (dir == Dir.UP) yKnockback = -6;
    if (dir == Dir.DOWN) yKnockback = 6;
    if (dir == Dir.LEFT) xKnockback = -6;
    if (dir == Dir.RIGHT) xKnockback = 6;
    hurtTime = 10;
  }

  public void takeItem(ItemEntity item) {
    item.take(this);
    Sound.ITEM.play();
  }

  public boolean touchedBy(Entity e) {
    return false;
  }
  
  public boolean use() {
    int dx = xr * dir.dx();
    int dy = yr * dir.dy();
    List<Tile> tiles = getMap().getTiles(loc.x, loc.y, loc.x + dx, loc.y + dy);
    for (Tile tile : tiles) {
      interact(tile, rightHand);
    }
    attack();
    return true;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void die() {
    isAlive = false;
    Sound.ENEMY_DIE.play();
    drop(new Heart(5));
    remove();
    new Mob(world);
  }

  public void drop(Item item){
    ItemEntity drop = new ItemEntity(item, world);
    drop.moveTo(loc.x, loc.y);
  }

  public Brain getBrain() {
    return brain;
  }

  public int getHP(){
    return hp;
  }

  public boolean isValidTile(Tile tile) {
    return tile.canPass() || loc.z > 0;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

}
