package caffeine.entity;



public class PlayerEntity extends Mob{

  public PlayerEntity(int id){
    super(id);
  }


  public void takeItem(ItemEntity item){
    item.take(this);
  }
}
