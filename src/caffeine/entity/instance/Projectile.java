package caffeine.entity.instance;

import caffeine.Rule;
import caffeine.action.instance.Hurt;
import caffeine.action.instance.Move;
import caffeine.entity.Entity;
import caffeine.view.Animation;

public class Projectile extends Entity {
  protected Entity owner;
  Move move;
  
  public Projectile(final Entity owner) {
    super(owner.loc().copy());
    this.owner = owner;
    
    loc.translate(owner.motion().facing(), owner.size());
    
    move = Move.fetch(owner.motion().facing());
    motion.speed(12);
    size = 4;
    
    int[] sprites = { 8 };
    anim = new Animation(sprites, 1, false);
    
    motion().validLocRule(new Rule() {
      public boolean appliesTo(Object o) {
        return true;
      }
    });
    loc().tile().add(this);
  }
  
  public void tick() {
    if (motion.validMove(move, this)) {
      move.performBy(this);
      for (Entity e : loc.tile().occupants()) {
        if (!(e instanceof Projectile) && !e.equals(owner)
            && hitbox().intersects(e.hitbox())) {
          new Hurt(1, this).performBy(e);
        }
      }
    } else {
      loc.tile().remove(this);
    }
  }
}
