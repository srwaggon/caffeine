package caffeine.entity.brain;

import caffeine.action.Move;
import caffeine.entity.Mob;
import caffeine.world.Dir;
import caffeine.world.Map;

public class Brain {
  protected Mob self;

  protected boolean flip;
  protected Dir dir = Dir.pickOneAtRandom();
  protected int moveTimer, waitTimer = 0;

  public Brain(Mob self) {
    this.self = self;
    self.setBrain(this);

  }

  public static Mob embody(Map map) {
    return new Brain(new Mob(map)).getSelf();
  }

  public Mob getSelf() {
    return self;
  }

  public void tick() {
    flip = !flip;
    if (flip) {
      if (waitTimer-- > 0){
        ;
      } else if (moveTimer-- > 0) {
        if (!new Move(dir).performBy(self)) {
          dir = dir.next();
        }
      } else {
        self.jump();
        dir = Dir.pickOneAtRandom();
        waitTimer = 10 + (int) (Math.random() * 6) * 60;
        moveTimer = 10 + (int) (Math.random() * 3) * 30;
      }
    }
  }

}

