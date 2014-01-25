package caffeine.net.packet;

import caffeine.Caffeine;
import caffeine.entity.Mob;

public class UseRightPacket extends CaffeinePacket {
  
  final String USERNAME;
  
  public UseRightPacket(String username) {
    super(CaffeineCode.USERIGHT);
    this.USERNAME = username;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
    Mob m = ((Mob) caffeine.getEntity(USERNAME));
    m.useRightHand();
  }
  
}
