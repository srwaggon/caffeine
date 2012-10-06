package caffeine.entity.brain;

import caffeine.entity.Mob;

public class Brain {
  protected Mob self;

  public Brain(Mob self) {
    this.self = self;
    self.setBrain(this);

  }

  public Mob getSelf() {
    return self;
  }

  public void tick() {

  }

}

