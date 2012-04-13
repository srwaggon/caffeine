package caffeine;

import caffeine.entity.Actor;
import caffeine.net.ClientWorker;

public class Player {
  protected ClientWorker client = null;
  protected Actor player = null;
  
  public Player(Game game) {
    player = new Actor();
  }
  
  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
  }
  
  public Actor actor() {
    return player;
  }
  
}
