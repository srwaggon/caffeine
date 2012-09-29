package caffeine.entity.brain;

import caffeine.entity.Mob;
import caffeine.world.Dir;

public class Brain {
  protected Mob self;

  protected boolean flip;
  protected Dir dir = Dir.pickOneAtRandom();
  protected int moveTimer, waitTimer = 0;

  public Brain(Mob self) {
    this.self = self;
    self.setBrain(this);

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
        if (!self.move(dir)) {
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

