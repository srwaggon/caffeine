package caffeine.net.packet;

import java.io.Serializable;

import com.sun.org.apache.bcel.internal.classfile.Code;

/* Packet Class
 * 
 * Represents a packet of data sent between GameServer and GameClient.
 * 
 * Abstract class so that there are no generic packets -- every packet will have a purpose.
 */
public class Packet extends link.packet.Packet implements Serializable {
  
  private static final long serialVersionUID = 2347350252515231743L;
  public static final Packet LoginFailure = new Packet(Code.LOGIN_FAILURE);
  public final Code code;
  
  /*
   * Require the use of this constructor; Require that every packet has a type
   */
  public Packet(Code code) {
    super(code);
    this.code = code;
  }
  
  @Override
  public Code getCode() {
    return code;
  }
}
