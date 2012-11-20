package caffeine.entity;

import java.io.Serializable;

public class PlayerEntity extends Mob implements Serializable {

  private static final long serialVersionUID = -1716762609995433563L;

  public PlayerEntity(int id) {
    super(id);
  }

  public void takeItem(ItemEntity item) {
    item.take(this);
  }
}
