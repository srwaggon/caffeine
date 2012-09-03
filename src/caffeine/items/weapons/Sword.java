package caffeine.items.weapons;

import caffeine.entity.Entity;
import caffeine.sfx.Sound;


public class Sword extends Weapon {

  @Override
  public void onTake(Entity entity) {
  }

  @Override
  public int getSprite() {
    return 97;
  }

  public void playUseSound(){
    int random = (int) (Math.random() * 4);
    if (random == 0)
      Sound.SWORD1.play();
    if (random == 1)
      Sound.SWORD2.play();
    if (random == 2)
      Sound.SWORD3.play();
    if (random == 3)
      Sound.SWORD4.play();
  }

}
