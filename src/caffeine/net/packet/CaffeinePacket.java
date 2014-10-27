package caffeine.net.packet;

import caffeine.Caffeine;

public abstract class CaffeinePacket extends link.packet.Packet {
  
  private static final long serialVersionUID = -3516416792644263748L;
  
  CaffeineCode code;
  
  public CaffeinePacket(CaffeineCode code) {
    super(code.ordinal());
    this.code = code;
  }
  
  public abstract void apply(Caffeine caffeine);
  
  public int getCode() {
    return code.ordinal();
  }
  
  public CaffeineCode getCaffeineCode() {
    return code;
  }
}
