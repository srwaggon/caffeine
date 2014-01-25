package caffeine.net.packet;

import caffeine.Caffeine;

public class EventPacket extends CaffeinePacket {
  
  private static final long serialVersionUID = -3978701864767248385L;
  public final Event EVENT;
  
  public EventPacket(Event event) {
    super(CaffeineCode.EVENT);
    EVENT = event;
  }
  
  public Event getEvent() {
    return EVENT;
  }
  
  @Override
  public void apply(Caffeine caffeine) {
  }
}
