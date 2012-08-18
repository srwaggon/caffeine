package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.World;

public class Brain {
  protected Mob self;

  public Brain(Mob self) {
    this.self = self;
    self.setBrain(this);
  }

  public static Mob embody(World world) {
    return new Brain(new Mob(world)).getSelf();
  }

  public Mob getSelf() {
    return self;
  }

  public void tick() {
  }
}
