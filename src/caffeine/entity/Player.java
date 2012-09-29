package caffeine.entity;



public class Player extends Mob{

  public Player(int id){
    this.id = id;
  }

  public void takeItem(ItemEntity item){
    item.take(this);
  }
}
