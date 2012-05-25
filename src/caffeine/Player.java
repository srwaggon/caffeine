package caffeine;

import caffeine.entity.Entity;
import caffeine.net.ClientWorker;

public class Player {
  protected ClientWorker client = null;
  protected Entity playerEntity = null;
  
  public Player(Game game) {
    playerEntity = new Entity();
    // playerEntity.setBrain(new PlayerBrain(game));
  }
  
  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
  }
  
  public Entity getEntity() {
    return playerEntity;
  }
  
}
