package caffeine.net.packet;

import caffeine.Caffeine;

public class FatalErrorPacket extends CaffeinePacket {
  
  private static final long serialVersionUID = 1219016604841884266L;
  
  private final String errorMessage;
  private final int errorCode;
  
  public FatalErrorPacket(String errorMessage, int errorCode) {
    super(CaffeineCode.FATAL_ERROR);
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }
  
  public String getMessage() {
    return errorMessage;
  }
  
  public int getErrorCode() {
    return errorCode;
  }
  
  @Override
  public String toString() {
    return String.format("FatalErrorPacket{%s}", errorMessage);
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
  
}
