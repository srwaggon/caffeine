package caffeine.entity;

import caffeine.world.World;

public class Boulder extends Mob {
  public Boulder(World w) {
    super(w);
    spriteID = 1;
    xr = 15;
    yr = 15;
  }
}
