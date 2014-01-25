package caffeine.net.packet;

import caffeine.Caffeine;

public class LoginPacket extends CaffeinePacket {
  
  private static final long serialVersionUID = -2411669515698693576L;
  public final String USERNAME;
  public final String PASSWORD;
  
  public LoginPacket(String username, String password) {
    super(CaffeineCode.LOGIN);
    USERNAME = username;
    PASSWORD = password;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
}
