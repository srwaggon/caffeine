package caffeine.entity.brain;

import java.util.LinkedList;
import java.util.List;

import caffeine.action.Action;
import caffeine.entity.Entity;
import caffeine.world.World;

public class Brain {
  protected List<Action> actionPlan = new LinkedList<Action>();
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

  public List<Action> next() {
    return new LinkedList<Action>();
  }
}
