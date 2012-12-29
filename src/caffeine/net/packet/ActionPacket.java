package caffeine.net.packet;


public class ActionPacket extends Packet {
  public final String USERNAME;

  public ActionPacket(Packet.Code code, String username) {
    super(code);
    USERNAME = username;
  }
}
