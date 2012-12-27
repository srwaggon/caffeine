package caffeine.net.packet;

import caffeine.world.Map;

public class MapPacket extends Packet {
  private static final long serialVersionUID = 914392197108468786L;
  public final Map MAP;

  public MapPacket(Map map) {
    super(Code.MAP);
    MAP = map;
  }

}
