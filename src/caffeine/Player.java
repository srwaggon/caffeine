package caffeine;

import caffeine.entity.PlayerEntity;
import caffeine.net.ClientWorker;

public class Player {
  private ClientWorker client = null;
  private final PlayerEntity player = null;
  
  public Player(ClientWorker cw) {
    client = cw;
  }
  
}
