package caffeine.action;

import java.awt.Rectangle;

import caffeine.entity.Entity;

public class Push implements Action {
  protected Entity pushee;

  public Push(Entity who) {
    pushee = who;
  }

  @Override
  public boolean performBy(Entity performer) {
    pushee.actionPlans.clear();

    Rectangle pusherBox = performer.getHitbox();
    Rectangle pusheeBox = pushee.getHitbox();
    double xa = 0, ya = 0;


    if (pusherBox.x < pusheeBox.x && pusheeBox.x < pusherBox.x + pusherBox.width) {
      // getting pushed right
      xa = (pusherBox.x + pusherBox.width) - pusheeBox.x;
    } else {
      // getting pushed
      xa = pusherBox.x - (pusheeBox.x + pusheeBox.width);
    }


    if (pusherBox.y < pusheeBox.y && pusheeBox.y < pusherBox.y + pusherBox.height) {
      ya = (pusherBox.y + pusherBox.height) - pusheeBox.y;
    } else {
      ya = pusherBox.y - (pusheeBox.y + pusheeBox.height);
    }

    return pushee.move(xa, ya, true);
  }
}
