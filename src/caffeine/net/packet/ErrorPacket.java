package caffeine.net.packet;

import caffeine.Caffeine;

public class ErrorPacket extends CaffeinePacket {
  
  private static final long serialVersionUID = -9215542412509089650L;
  
  String errorMessage;
  
  public ErrorPacket(String errorMessage) {
    super(CaffeineCode.ERROR);
    this.errorMessage = errorMessage;
  }
  
  public String getMessage() {
    return errorMessage;
  }
  
  @Override
  public String toString() {
    return String.format("ErrorPacket{%s}", errorMessage);
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
  
}
