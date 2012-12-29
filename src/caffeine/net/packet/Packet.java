package caffeine.net.packet;

import java.io.Serializable;


/* Packet Class
 * 
 * Represents a packet of data sent between GameServer and GameClient.
 * 
 * Abstract class so that there are no generic packets -- every packet will have a purpose.
 */
public class Packet implements Serializable {

  public enum Code {
    LOGIN, LOGIN_FAILURE, MAP, EVENT, MOVE, JUMP, USERIGHT;
  }

  private static final long serialVersionUID = 2347350252515231743L;
  public static final Packet LoginFailure = new Packet(Code.LOGIN_FAILURE);
  public final Code code;

  /* Require the use of this constructor;
   * Require that every packet has a type
   */
  public Packet(Code code) {
    this.code = code;
  }

  public Code getCode(){
    return code;
  }


}
