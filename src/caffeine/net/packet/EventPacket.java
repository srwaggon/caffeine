package caffeine.net.packet;


public class EventPacket extends Packet {

  private static final long serialVersionUID = -3978701864767248385L;
  public final Event EVENT;

  public EventPacket(Event event) {
    super(Packet.Code.EVENT);
    EVENT = event;
  }

  public Event getEvent() {
    return EVENT;
  }
}
