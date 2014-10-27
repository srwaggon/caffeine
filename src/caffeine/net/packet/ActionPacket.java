package caffeine.net.packet;

import caffeine.Caffeine;

public abstract class ActionPacket extends CaffeinePacket {

  private static final long serialVersionUID = 2563337983388650221L;
  
  public final String USERNAME;
  
  public ActionPacket(CaffeineCode code, String username) {
    super(code);
    USERNAME = username;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
}
