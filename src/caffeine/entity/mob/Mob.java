package caffeine.entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import pixl.Screen;
import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.entity.mob.brain.Brain;
import caffeine.entity.mob.brain.RandomBrain;
import caffeine.items.Heart;
import caffeine.items.Item;
import caffeine.items.ItemType;
import caffeine.items.weapons.Sword;
import caffeine.sfx.Sound;
import caffeine.world.Dir;
import caffeine.world.Map;
import caffeine.world.tile.Tile;

public class Mob extends Entity {
  private static final long serialVersionUID = -3841320632173330369L;
  // primitive Fields
  protected boolean flip = false;
  protected boolean isAlive = true;
  protected int hp = 200;
  protected int hurtTime;
  protected int power = 10;
  protected double speed = 0.7;
  protected int walkDist = 0;

  // Object Fields
  protected Brain brain;
  protected Item leftHand;
  protected Item rightHand = new Sword();
  protected List<Item> inventory = new ArrayList<Item>();

  public Mob() {
    width = 8;
    length = 8;
    brain = new RandomBrain(this);
  }

  public Mob(String id) {
    super(id);
    width = 8;
    length = 8;
    brain = new Brain(this);
  }

  @Override
  public void addItem(Item item) {
    if (item.getType() == ItemType.weapon) {
      rightHand = item;
    } else {
      inventory.add(item);
    }
  }

  public void attack() {
    // use this entity's range to hurt the entities within a given proximity
    if (dir == Dir.N)
      hurt(x, y - (2 * getWidth()), x, y);
    if (dir == Dir.E)
      hurt(x, y, x + (2 * getLength()), y);
    if (dir == Dir.S)
      hurt(x, y, x, y + (2 * getWidth()));
    if (dir == Dir.W)
      hurt(x - (2 * getLength()), y, x, y);
  }

  public void die() {
    isAlive = false;
    Sound.ENEMY_DIE.play();
    drop(new Heart(5));
    remove();
    new Mob(ID).setMap(map);
  }

  public void drop(Item item) {
    ItemEntity ie = new ItemEntity(item);
    ie.setLoc(x, y);
    map.addEntity(ie);
  }

  public void equipItem(Item item) {
    ItemType itemType = item.getType();
    if (itemType == ItemType.tool || itemType == ItemType.weapon) {
      rightHand = item;
    }
  }

  public Brain getBrain() {
    return brain;
  }

  @Override
  public int getHP() {
    return hp;
  }

  @Override
  public void heal(int n) {
    hp += n;
  }

  public void hurt(double left, double top, double right, double bottom) {
    List<Entity> entities = getMap().getEntities(left, top, right, bottom);
    for (Entity entity : entities) {
      if (!entity.equals(this)) {
        entity.takeDamage(power, dir);
      }
    }
  }

  public void interact(double left, double top, double right, double bottom, Item item) {
    List<Tile> tiles = getMap().getTiles(left, top, right, bottom);
    for (Tile tile : tiles) {
      tile.interact(this, item, dir);
    }
  }

  public boolean isAlive() {
    return isAlive;
  }

  @Override
  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC() || z > 0;
  }

  int jump = 25;
  public void jump() {
    if (z <= 0) {
      za = jump / ticksPerSecond;
      Sound.JUMP.play();
    }
  }

  public void render(Screen screen) {
    int sprite = this.sprite + dir.ordinal();
    int h = Map.tileSize / 2;
    int w = 2 * getWidth();
    screen.render(sprite, (int) x - w, (int) (y - w - z));
  }

  public void setBrain(Brain brain) {
    this.brain = brain;
  }

  @Override
  public void takeDamage(int dmg, Dir dir) {
    hp -= dmg;
    Sound.HURT.play();
    hurtTime = dmg * 3;
  }

  @Override
  public void takeItem(ItemEntity item) {
    item.take(this);
  }

  @Override
  public void tick(double ticksPerSecond) {
    super.tick(ticksPerSecond);

    if (brain != null) {
      brain.tick();
    }

    if (hurtTime > 0) {
      hurtTime--;
    }

    if (hp <= 0) {
      die();
    }
  }

  @Override
  public boolean touchedBy(Entity entity) {
    return false;
  }

  public boolean useLeftHand() {
    if (leftHand != null) {
      leftHand.playUseSound();
    }
    return true;
  }

  public boolean useRightHand() {
    if (rightHand != null) {
      if (dir == Dir.N)
        interact(x, y - (2 * getWidth()), x, y, rightHand);
      if (dir == Dir.E)
        interact(x, y, x + (2 * getLength()), y, rightHand);
      if (dir == Dir.S)
        interact(x, y, x, y + (2 * getWidth()), rightHand);
      if (dir == Dir.W)
        interact(x - (2 * getLength()), y, x, y, rightHand);

      if (rightHand.getType() == ItemType.weapon) {
        attack();
      }
      rightHand.playUseSound();
    }

    return true;
  }

}
