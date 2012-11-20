package caffeine.entity.brain;

import java.io.Serializable;

import caffeine.entity.Mob;

public class Brain implements Serializable {

  private static final long serialVersionUID = 7566704889525308666L;
  protected Mob self;

  public Brain(Mob self) {
    this.self = self;
  }

  public Mob getSelf() {
    return self;
  }

  public void tick() {
  }

}
