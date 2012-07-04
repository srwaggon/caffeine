package caffeine.entity;

import caffeine.view.Animation;
import caffeine.view.Sprite;
import caffeine.world.World;

public class Boulder extends Entity {
  public Boulder(World w) {
    super(w);
    anim = new Animation(new Sprite(1));
    size = 32;
    speed = 24;
  }
}
