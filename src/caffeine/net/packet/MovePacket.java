package caffeine.net.packet;

import caffeine.world.Dir;


public class MovePacket extends Packet {
  private static final long serialVersionUID = -2791171004223148512L;
  public final String USERNAME;
  public final Dir DIR;

  public MovePacket(final String username, final Dir dir) {
    super(Packet.Code.MOVE);
    USERNAME = username;
    DIR = dir;
  }

  public String toString() {
    return String.format("MovePacket{%s, %s}", USERNAME, DIR);
  }

}
