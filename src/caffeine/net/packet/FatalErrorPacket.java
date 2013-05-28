package caffeine.net.packet;

public class FatalErrorPacket extends Packet {
  
  private final String errorMessage;
  private final int errorCode;
  
  public FatalErrorPacket(String errorMessage, int errorCode) {
    super(Code.FATAL_ERROR);
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
  
}
