package caffeine.entity;

import caffeine.view.Animation;
import caffeine.view.Sprite;
import caffeine.world.Loc;
import caffeine.world.World;

public class Boulder extends Entity {
  public Boulder(World w, Loc l) {
    super(w, l);
    anim = new Animation(new Sprite(1));
    size = 32;
  }
}
