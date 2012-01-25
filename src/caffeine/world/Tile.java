package caffeine.world;

import java.util.Collection;
import java.util.HashMap;

import caffeine.entity.Entity;

public class Tile{
  public static Tile block = new Tile(false, 0, 1, '#');
  public static Tile safe = new Tile(true, 0, 0, '.');

  protected HashMap<Integer, Entity> occupants = new HashMap<Integer, Entity>();
  protected boolean pass = true;
  protected int damage = 0;
  protected int spriteID = 0;
  protected char symbol;

  public Tile(){}

  public Tile(char c){
    symbol = c;
    if(c == '#'){
      pass = false;
      spriteID = 1;
    }
  }

  private Tile(boolean pass, int damage, int spriteID, char symbol){
    this.pass = pass;
    this.damage = damage;
    this.spriteID = spriteID;
    this.symbol = symbol;
  }

  public void add(Entity e){
    occupants.put(e.getID(), e);
  }

  public Collection<Entity> occupants(){
    return occupants.values();
  }

  public boolean isEmpty(){
    return occupants.isEmpty();
  }

  public boolean pass(){
    return pass;
  }

  public void remove(Entity e){
    occupants.remove(e.getID());
  }

  public boolean safe(){
    return damage <= 0;
  }

  public String toString(){
    return ""+symbol;
  }

  public int spriteID() {
    return spriteID;
  }

  public void tick() {
  }
}