package caffeine.entity;

import caffeine.Rule;
import caffeine.action.Die;
import caffeine.action.Move;
import caffeine.view.Animation;
import caffeine.world.Direction;
import caffeine.world.Location;

public class Projectile extends Actor {
  protected Entity owner;
  protected Direction dir;
  int lifespan;
  
  public Projectile(final Actor owner, int lifespan) {
    super(owner.loc());
    loc.map().add(this);
    this.owner = owner;
    this.lifespan = lifespan;
    dir = owner.motion().facing();
    motion.speed(12);
    size = 15;
    
    int[] sprites = { 8 };
    anim = new Animation(sprites, 1, false);
    
    motion().validLocRule(new Rule() {
      public boolean appliesTo(Object o) {
        if (o instanceof Location) {
          Location loc = (Location) o;
          return loc.validLocation() && loc.tile().pass();
        }
        return false;
      }
    });
  }
  
  public void tick() {
    if (lifespan-- > 0) {
      new Move(dir).perform(this);
      tile().remove(this);
      motion.tick();
      tile().add(this);
    } else {
      new Die("LOLOOLLOLO").perform(this);
    }
  }
}
