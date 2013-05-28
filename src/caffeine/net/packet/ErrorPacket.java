package caffeine.net.packet;

public class ErrorPacket extends Packet {
  
  private static final long serialVersionUID = -9215542412509089650L;
  
  String errorMessage;
  
  public ErrorPacket(String errorMessage) {
    super(Packet.Code.ERROR);
    this.errorMessage = errorMessage;
  }
  
  @Override
  public String toString() {
    return String.format("ErrorPacket{%s}", errorMessage);
  }
  
}
