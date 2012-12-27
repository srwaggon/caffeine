package caffeine.net.packet;


public class LoginPacket extends Packet {
  private static final long serialVersionUID = -2411669515698693576L;
  public final String USERNAME;
  public final String PASSWORD;

  public LoginPacket(String username, String password) {
    super(Packet.Code.LOGIN);
    USERNAME = username;
    PASSWORD = password;
  }
}
