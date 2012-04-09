package caffeine.entity.instance;

import caffeine.entity.Entity;
import caffeine.view.Animation;
import caffeine.view.Sprite;
import caffeine.world.Loc;

public class Boulder extends Entity {
  public Boulder(Loc l) {
    super(l);
    anim = new Animation(new Sprite(1));
    size = 32;
  }
}
