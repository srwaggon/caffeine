package caffeine.net.packet;

import caffeine.Caffeine;

public abstract class ActionPacket extends CaffeinePacket {
  public final String USERNAME;
  
  public ActionPacket(CaffeineCode code, String username) {
    super(code);
    USERNAME = username;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
}
