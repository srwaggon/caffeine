package caffeine.net.packet;

import java.io.Serializable;


/* Packet Class
 * 
 * Represents a packet of data sent between GameServer and GameClient.
 * 
 * Abstract class so that there are no generic packets -- every packet will have a purpose.
 */
public class Packet implements Serializable {
  private static final long serialVersionUID = 2347350252515231743L;
  public static final Packet LoginFailure = new Packet(Packets.LoginFailure);
  public final Packets type;

  /* Require the use of this constructor;
   * Require that every packet has a type
   */
  public Packet(Packets type) {
    this.type = type;
  }

  public Packets getType(){
    return type;
  }


}
