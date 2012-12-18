package caffeine.net.packet;


/*
 * Packet Class
 * 
 * Represents a packet of data sent between GameServer and GameClient.
 * 
 * Abstract class so that there are no generic packets -- every packet will have a purpose.
 */
public abstract class Packet {

  PacketType type;

  /* Require the use of this constructor;
   * Require that every packet has a type
   */
  public Packet(PacketType type) {
    this.type = type;
  }

  enum PacketType {
    ATTACK, LOGIN, MOVE;
  }

  public PacketType getType(){
    return type;
  }

}
