package caffeine.entity.brain;

import caffeine.entity.Entity;
import caffeine.world.World;

public class Brain {
  protected Entity self;

  public Brain(Entity self) {
    this.self = self;
    self.setBrain(this);
  }

  public static Entity embody(World world) {
    return new Brain(new Entity(world)).getEntity();
  }

  public Entity getEntity() {
    return self;
  }

  public void tick() {
  }
}
