package caffeine;

import caffeine.entity.Entity;
import caffeine.net.ClientWorker;

public class Player {
  protected ClientWorker client = null;
  protected Entity playerEntity = null;
  
  public Player(Game game) {
    playerEntity = new Entity();
  }
  
  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
  }
  
  public Entity entity() {
    return playerEntity;
  }
  
}
