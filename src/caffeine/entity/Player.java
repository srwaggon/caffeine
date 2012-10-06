package caffeine.entity;



public class Player extends Mob{

  public Player(int id){
    super(id);
  }


  public void takeItem(ItemEntity item){
    item.take(this);
  }
}
