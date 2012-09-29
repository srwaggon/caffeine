package caffeine.entity;

import java.util.Collection;
import java.util.List;

import caffeine.gfx.Screen;
import caffeine.items.Item;
import caffeine.world.Dir;
import caffeine.world.Loc;
import caffeine.world.Map;
import caffeine.world.tile.Tile;

/**
 * base class representing objects in the world. Entities have a location
 * 
 * @author srwaggon
 * 
 */
public class Entity {
  /* static fields */
  protected static int numEntities = 0;
  /* primitive fields */
  protected int id = Entity.numEntities++;
  protected boolean removed = false;

  protected int xr = 2;
  protected int yr = 2;
  public int sprite = 128;
  protected int speed = 1;

  /* object fields */
  protected Dir dir = Dir.DOWN;
  protected Loc loc;
  protected Map map;

  /* CONSTRUCTORS */
  public Entity() {
  }

  public Entity(int id){
    this.id = id;
  }

  public void init(Map map){
    this.map = map;
    loc = new Loc(map.getID(), 32, 32, 0);
    map.addEntity(this);
  }

  public void tick() {
  }

  public boolean move(Dir dir){
    boolean notStopped = move2(dir.dx() * speed, 0);
    return notStopped && move2(0, dir.dy() * speed);
  }

  public boolean move(int xa, int ya){
    boolean notStopped = move2(xa, 0);
    return notStopped && move2(0, ya);
  }

  public boolean move2(int xa, int ya) {
    Map map = getMap();

    if (xa != 0 && ya != 0){
      return false;
    }

    setDir(xa, ya);

    int nx = loc.x + xa; // next x
    int ny = loc.y + ya; // next y

    List<Tile> nextTiles = map.getTiles(nx - xr, ny - yr, nx + xr, ny + yr);
    for (Tile t : nextTiles) {
      if (!isValidTile(t)) {
        return false;
      }
    }

    // Check collision with each entity.
    Collection<Entity> entities = map.entities();
    for (Entity entity : entities) {

      // if they currently intersect, move freely.
      if (entity.equals(this)) {
        continue;
      }

      if (intersects(entity)) {
        continue;
      }

      // If they're going to intersect, inform them.
      if (entity.intersects(nx - xr, ny - yr, nx + xr, ny + yr)) {
        if (!entity.touchedBy(this)) {
          return false;
        }
      }
    }

    // Change location.
    loc.x += xa;
    loc.y += ya;

    for (Tile t : nextTiles){
      t.onEnter(this);
    }

    return true;
  }

  public void moveTo(int x, int y){
    loc.x = x;
    loc.y = y;
  }

  public void heal(int n){
  }

  public boolean push(Entity pushee, int xa, int ya) {
    return pushee.move(xa, ya);
  }

  public final void render(Screen screen) {
    screen.render(sprite, loc.x - Map.tileSize / 2, loc.y - Map.tileSize / 2
        - loc.z);
  }

  public boolean intersects(int x0, int y0, int x1, int y1) {
    return !(loc.x + xr < x0 || loc.y + yr < y0 || loc.x - xr > x1 || loc.y
        - yr > y1);
  }

  public boolean intersects(Entity e) {
    return !equals(e)
        && intersects(e.loc.x - e.xr, e.loc.y - e.yr, e.loc.x + e.xr, e.loc.y
            + e.yr);
  }

  public void knockback(int x, int y){ }

  public boolean touchedBy(Entity entity) {
    return true;
  }

  public void takeDamage(int dmg) { }

  public void takeDamage(int dmg, Dir dir){ }

  public void takeItem(ItemEntity item) { }

  /* ACCESSORS */

  public Dir getDirection() {
    return dir;
  }

  public void setDir(Dir dir) {
    this.dir = dir;
  }

  public void setDir(int xa, int ya){
    int absxa = Math.abs(xa);
    int absya = Math.abs(ya);

    if (xa < 0 && absxa > absya) setDir(Dir.LEFT);
    if (xa > 0 && absxa > absya) setDir(Dir.RIGHT);
    if (ya < 0 && absxa < absya) setDir(Dir.UP);
    if (ya > 0 && absxa < absya) setDir(Dir.DOWN);

  }

  public void setSprite(int sprite) {
    this.sprite = sprite;
  }

  public static int getPopulation() {
    return Entity.numEntities;
  }

  public int getID() {
    return id;
  }

  public Loc getLoc() {
    return loc;
  }

  public void setLoc(Loc loc) {
    this.loc = loc;
  }

  public Map getMap() {
    return map;
  }

  public int getSpeed() {
    return speed;
  }

  public boolean isValidTile(Tile tile) {
    return !tile.blocksNPC();
  }

  public boolean isRemoved() {
    return removed;
  }

  public void remove() {
    removed = true;
  }

  @Override
  public String toString() {
    return "entity " + id + " " + sprite + " " + loc.toString() + "\n";
  }

  @Override
  public void finalize() {
    try {
      Entity.numEntities--;
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public int getHP() {
    return 0;
  }

  public void addItem(Item item) {
  }
}
