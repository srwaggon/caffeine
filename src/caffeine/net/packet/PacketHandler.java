package caffeine.net.packet;

import com.sun.xml.internal.ws.api.message.Packet;


public interface PacketHandler {

  public void handle(Packet packet);

}
