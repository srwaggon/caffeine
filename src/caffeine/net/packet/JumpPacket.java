package caffeine.net.packet;

import caffeine.Caffeine;

public class JumpPacket extends CaffeinePacket {
  public final String USERNAME;
  
  public JumpPacket(String username) {
    super(CaffeineCode.JUMP);
    USERNAME = username;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
}
