package caffeine.net.packet;

import caffeine.Caffeine;
import caffeine.world.Map;

public class MapPacket extends CaffeinePacket {
  private static final long serialVersionUID = 914392197108468786L;
  public final Map MAP;
  
  public MapPacket(Map map) {
    super(CaffeineCode.MAP);
    MAP = map;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
    caffeine.putMap(MAP);
  }
  
}
