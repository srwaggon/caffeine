package caffeine.net.packet;

import caffeine.Caffeine;
import caffeine.entity.mob.Mob;

public class UseRightPacket extends CaffeinePacket {
  
  private static final long serialVersionUID = 7251779307882630706L;
  
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
