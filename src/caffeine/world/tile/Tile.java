package caffeine.world.tile;

import java.util.ArrayList;

import caffeine.entity.Entity;

public class Tile{
  protected ArrayList<Entity> occupants = new ArrayList<Entity>();

  private TileObject type;

  public Tile(){type = new Grass();}

  public Tile(char c){
    if(c == '#'){
      setRock();
    }else if(c == '!'){
      setLava();
    }else if(c == '-'){
      setIce();
    }else{
      setGrass();
    }
  }

  public Tile(TileObject t){type = t;}

  public void add(Entity e){occupants.add(e);}

  public ArrayList<Entity> entities(){return occupants;}

  public double drag(){return type.drag();}

  public TileObject type(){return type;}

  public boolean pass(){return type.pass();}

  public boolean isEmpty(){return occupants.isEmpty();}

  public boolean safe(){return type.safe();}

  public void remove(Entity e){
    occupants.remove(e);
  }

  public void setGrass(){type = new Grass();}

  public void setIce(){type = new Ice();}

  public void setRock(){type = new Rock();}

  public void setWarp(Warp w){type = w;}

  public void setLava(){type = new Lava();}

  public String toString(){return type.toString();}
}