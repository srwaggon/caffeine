package caffeine.net.packet;

import caffeine.Caffeine;
import caffeine.world.Dir;

public class MovePacket extends CaffeinePacket {
  private static final long serialVersionUID = -2791171004223148512L;
  public final String USERNAME;
  public final Dir DIR;
  
  public MovePacket(final String username, final Dir dir) {
    super(CaffeineCode.MOVE);
    USERNAME = username;
    DIR = dir;
  }
  
  public String toString() {
    return String.format("MovePacket{%s, %s}", USERNAME, DIR);
  }
  
  @Override
  public void apply(Caffeine caffeine) {
    caffeine.getMob(USERNAME).moveDirInSpeed(DIR);
  }
  
}
