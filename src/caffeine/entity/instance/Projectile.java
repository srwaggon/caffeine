package caffeine.entity.instance;

import caffeine.Rule;
import caffeine.action.Hurt;
import caffeine.action.Move;
import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.view.Animation;
import caffeine.world.Location;

public class Projectile extends Actor {
  protected Entity owner;
  Move move;
  
  public Projectile(final Actor owner) {
    super(owner.loc().copy());
    this.owner = owner;
    
    move = Move.fetch(owner.motion().facing());
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
    if (motion.validMove(move, this)) {
      move.perform(this);
      motion.move(this);
      for (Entity e : loc.tile().occupants()) {
        if (e instanceof Actor && !(e instanceof Projectile)
            && !e.equals(owner) && hitbox().intersects(e.hitbox())) {
          Actor a = (Actor) e;
          new Hurt(1, this).perform(a);
        }
      }
    } else {
      loc.tile().remove(this);
    }
  }
}
