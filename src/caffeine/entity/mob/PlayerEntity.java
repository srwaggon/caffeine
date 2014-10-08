package caffeine.entity.mob;

import java.io.Serializable;

import caffeine.entity.ItemEntity;

public class PlayerEntity extends Mob implements Serializable {

  private static final long serialVersionUID = -1716762609995433563L;

  public PlayerEntity(String id) {
    super(id);
    speed = 1.2;
  }

  public void takeItem(ItemEntity item) {
    item.take(this);
  }

}
