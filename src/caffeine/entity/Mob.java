package caffeine.entity;

import java.util.ArrayList;
import java.util.List;

import caffeine.entity.brain.Brain;
import caffeine.entity.brain.RandomBrain;
import caffeine.gfx.Screen;
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
  protected int xKnockback, yKnockback;
  protected int xa, ya, za = 0;
  protected int ticktime = 0;
  protected int walkDist = 0;

  // Object Fields
  protected Brain brain;
  protected Item leftHand;
  protected Item rightHand = new Sword();
  protected List<Item> inventory = new ArrayList<Item>();

  public Mob() {
    xr = 3;
    yr = 3;
    brain = new RandomBrain(this);
  }

  public Mob(String id) {
    super(id);
    xr = 6;
    yr = 6;
    brain = new Brain(this);
  }

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
      hurt(x, y - (2 * yr), x, y);
    if (dir == Dir.E)
      hurt(x, y, x + (2 * xr), y);
    if (dir == Dir.S)
      hurt(x, y, x, y + (2 * yr));
    if (dir == Dir.W)
      hurt(x - (2 * xr), y, x, y);
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

  public int getHP() {
    return hp;
  }

  public void heal(int n) {
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
    for (Tile tile : tiles) {
      tile.interact(this, item, dir);
    }
  }

  public boolean isAlive() {
    return isAlive;
  }

  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC() || z > 0;
  }

  public void jump() {
    if (z == 0) {
      za = 6;
      Sound.JUMP.play();
    }
  }

  public void knockback(int x, int y) {
    xKnockback = x;
    yKnockback = y;
  }

  public boolean move(int xa, int ya) {
    if (xKnockback > 0) {
      xKnockback--;
      super.move2(1, 0);
    }
    if (xKnockback < 0) {
      xKnockback++;
      super.move2(-1, 0);
    }
    if (yKnockback > 0) {
      yKnockback--;
      super.move2(0, 1);
    }
    if (yKnockback < 0) {
      yKnockback++;
      super.move2(0, -1);
    }
    if (hurtTime > 0)
      return true;

    if (xa != 0 || ya != 0) {
      walkDist++;
      if (ya < 0) dir = Dir.N;
      if (xa > 0) dir = Dir.E;
      if (ya > 0) dir = Dir.S;
      if (xa < 0) dir = Dir.W;
    }
    return super.move(xa, ya);
  }

  public void render(Screen screen) {
    int sprite = this.sprite + dir.ordinal();
    screen.render(sprite, x - Map.tileSize / 2, y - Map.tileSize / 2 - z);
  }

  public void setBrain(Brain b) {
    brain = b;
  }

  public void takeDamage(int dmg, Dir dir) {
    hp -= dmg;
    Sound.HURT.play();

    if (dir == Dir.N)
      yKnockback = -power;
    if (dir == Dir.S)
      yKnockback = power;
    if (dir == Dir.W)
      xKnockback = -power;
    if (dir == Dir.E)
      xKnockback = power;
    hurtTime = power;
  }

  public void takeItem(ItemEntity item) {
    item.take(this);
  }

  public void tick() {
    ticktime++;
    if (brain != null) {
      brain.tick();
    }


    if ((ticktime & 1) == 0) {
      if (z >= 0 ) {
        z += za--;
        if (z <= 0) {
          z = 0;
          za = 0;
        }
      }
    }
    move(xa, ya);
    xa = 0;
    ya = 0;

    if (hurtTime > 0)
      hurtTime--;

    if (hp <= 0)
      die();
  }

  public boolean touchedBy(Entity entity) {
    if (entity.dir == Dir.N)
      knockback(0, -6);
    if (entity.dir == Dir.S)
      knockback(0, 6);
    if (entity.dir == Dir.W)
      knockback(-6, 0);
    if (entity.dir == Dir.E)
      knockback(6, 0);
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
        interact(x, y - (2 * yr), x, y, rightHand);
      if (dir == Dir.E)
        interact(x, y, x + (2 * xr), y, rightHand);
      if (dir == Dir.S)
        interact(x, y, x, y + (2 * yr), rightHand);
      if (dir == Dir.W)
        interact(x - (2 * xr), y, x, y, rightHand);

      if (rightHand.getType() == ItemType.weapon) {
        attack();
      }
      rightHand.playUseSound();
    }

    return true;
  }

  public void setAccel(int dx, int dy) {
    xa = dx;
    ya = dy;
  }

}
