package caffeine.entity;



public class Player extends Mob{

  public Player(){
  }

  public boolean move(int xa, int ya){
    return super.move(xa, ya);
  }

  public void takeItem(ItemEntity item){
    item.take(this);
  }

  public void tick(){
    super.tick();
  }
}
