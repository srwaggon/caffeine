package caffeine.entity;

import java.util.ArrayList;
import java.util.List;

import caffeine.action.Move;
import caffeine.entity.brain.Brain;
import caffeine.items.Heart;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.items.weapons.Sword;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.Map;
import caffeine.world.tile.Tile;

public class Mob extends Entity {

  protected boolean flip = false;
  protected boolean isAlive = true;
  protected int hp = 3;
  protected int hurtTime;
  protected int power = 1;
  protected int range = 8;
  protected int xKnockback, yKnockback;
  public int xa, ya, za = 0;

  // Object Fields
  protected Brain brain;
  protected Item leftHand;
  protected Item rightHand = new Sword();
  protected List<Item> inventory = new ArrayList<Item>();


  public Mob(Map map) {
    super(map);
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
    new Move(xa, ya).performBy(this);

    if (hurtTime > 0) hurtTime--;

    if (hp <= 0) die();
  }

  public boolean move(int xa, int ya){
    if (xKnockback > 0) {
      xKnockback--;
      move2(1, 0);
    }
    if (xKnockback < 0) {
      xKnockback++;
      move2(-1, 0);
    }
    if (yKnockback > 0) {
      yKnockback--;
      move2(0, 1);
    }
    if (yKnockback < 0) {
      yKnockback++;
      move2(0, -1);
    }
    if (hurtTime > 0) return true;

    return super.move(xa, ya);
  }

  public void attack() {
    // use this entity's range to hurt the entities within a given proximity
    if (dir == Dir.UP)
      hurt(loc.x - range / 2, loc.y - range, loc.x + range / 2, loc.y - yr);
    if (dir == Dir.DOWN)
      hurt(loc.x - range / 2, loc.y + yr, loc.x + range / 2, loc.y + range);
    if (dir == Dir.LEFT)
      hurt(loc.x - range, loc.y - range / 2, loc.x - xr, loc.y + range / 2);
    if (dir == Dir.RIGHT)
      hurt(loc.x + xr, loc.y - range / 2, loc.x + range, loc.y + range / 2);

    rightHand.playUseSound();
  }

  public void heal(int n){
    hp += n;
  }

  public void hurt(int x0, int y0, int x1, int y1) {
    List<Entity> entities = getMap().getEntities(x0, y0, x1, y1);
    for (Entity entity : entities)
      if (!entity.equals(this))
        entity.takeDamage(power, dir);
  }

  public void interact(int x0, int y0, int x1, int y1, Item item) {
    List<Tile> tiles = getMap().getTiles(x0, y0, x1, y1);
    for(Tile tile : tiles) {
      tile.interact(this, item, dir);
    }
  }

  public void jump() {
    if (loc.z == 0) {
      za = 6;
      Sound.JUMP.play();
    }
  }

  public void addItem(Item item){
    if (item.getType() == ItemType.weapon){
      rightHand = item;
    } else {
      inventory.add(item);
    }
  }

  public void equipItem(Item item) {
    ItemType itemType = item.getType();
    if (itemType == ItemType.tool ||
        itemType == ItemType.weapon) {
      rightHand = item;
    }
  }

  public void knockback(int x, int y){
    xKnockback = x;
    yKnockback = y;
  }

  public void takeDamage(int dmg, Dir dir) {
    hp -= dmg;
    Sound.HURT.play();

    if (dir == Dir.UP) yKnockback = -power;
    if (dir == Dir.DOWN) yKnockback = power;
    if (dir == Dir.LEFT) xKnockback = -power;
    if (dir == Dir.RIGHT) xKnockback = power;
    hurtTime = 10;
  }

  public void takeItem(ItemEntity item) {
    item.take(this);
  }

  public boolean touchedBy(Entity entity) {
    if (entity.dir == Dir.UP) knockback(0, -6);
    if (entity.dir == Dir.DOWN) knockback(0, 6);
    if (entity.dir == Dir.LEFT) knockback(-6, 0);
    if (entity.dir == Dir.RIGHT) knockback(6, 0);
    return false;
  }

  public boolean useLeftHand() {
    return true;
  }

  public boolean useRightHand() {
    if (rightHand != null) {
      if (dir == Dir.UP)
        interact(loc.x - range / 2, loc.y - range, loc.x + range / 2, loc.y - yr, rightHand);
      if (dir == Dir.DOWN)
        interact(loc.x - range / 2, loc.y + yr, loc.x + range / 2, loc.y + range, rightHand);
      if (dir == Dir.LEFT)
        interact(loc.x - range, loc.y - range / 2, loc.x - xr, loc.y + range / 2, rightHand);
      if (dir == Dir.RIGHT)
        interact(loc.x + xr, loc.y - range / 2, loc.x + range, loc.y + range / 2, rightHand);

      if (rightHand.getType() == ItemType.weapon) {
        attack();
      }
    }

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
    new Mob(map);
  }

  public void drop(Item item){
    new ItemEntity(item, map).moveTo(loc.x, loc.y);
  }

  public Brain getBrain() {
    return brain;
  }

  public int getHP(){
    return hp;
  }

  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC() || loc.z > 0;
  }

  public void setBrain(Brain b) {
    brain = b;
  }

}
