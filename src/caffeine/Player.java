package caffeine;

import caffeine.entity.instance.PlayerEntity;
import caffeine.net.ClientWorker;

public class Player {
  protected ClientWorker client = null;
  protected PlayerEntity player = null;
  
  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
  }
  
  public Player(Game game) {
    player = new PlayerEntity(game.gui().input());
  }
  
  public PlayerEntity playerEntity() {
    return player;
  }
  
}
