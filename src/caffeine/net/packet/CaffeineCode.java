package caffeine.net.packet;

public enum CaffeineCode {
  LOGIN(LoginPacket.class), ERROR(ErrorPacket.class), FATAL_ERROR(
      FatalErrorPacket.class), MAP(MapPacket.class), EVENT(EventPacket.class), MOVE(
      MovePacket.class), JUMP(JumpPacket.class), USERIGHT(UseRightPacket.class);
  
  private Class<? extends CaffeinePacket> packetClass;
  
  CaffeineCode(Class<? extends CaffeinePacket> packetClass) {
    this.packetClass = packetClass;
  }
  
  public Class<? extends CaffeinePacket> getPacketClass() {
    return this.packetClass;
  }
  
  public static CaffeineCode get(int code) {
    return values()[code];
  }
}
