package caffeine.world.tile;

import java.util.ArrayList;

import caffeine.entity.Entity;

public class Tile{
  protected ArrayList<Entity> occupants = new ArrayList<Entity>();

  private TileType type;

  public Tile(){type = new Safe();}

  public Tile(char c){
    if(c == '#'){
      setRock();
    }else if(c == '!'){
      setLava();
    }else{
      setGrass();
    }
  }

  public Tile(TileType t){type = t;}

  public void add(Entity e){occupants.add(e);}

  public ArrayList<Entity> entities(){return occupants;}

  public TileType type(){return type;}

  public boolean pass(){return type.pass();}

  public boolean isEmpty(){return occupants.isEmpty();}

  public boolean safe(){return type.safe();}

  public void remove(Entity e){
    occupants.remove(e);
  }

  public void setGrass(){type = new Safe();}

  public void setRock(){type = new Block();}

  public void setWarp(Warp w){type = w;}

  public void setLava(){type = new Damage();}

  public String toString(){return type.toString();}
}