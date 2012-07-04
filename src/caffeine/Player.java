package caffeine;

import caffeine.entity.Entity;
import caffeine.net.ClientWorker;
import caffeine.view.InputHandler;
import caffeine.view.InputListener;

public class Player {
  protected boolean networkPlayer = false;
  protected ClientWorker client;
  protected Entity playerEntity;
  protected InputListener input = new InputHandler();
  protected Game game;

  public Player(Game game) {
    this.game = game;
  }

  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
    input = client.getInputListener();
  }

  public Entity getEntity() {
    return playerEntity;
  }

  public InputListener getInputListener() {
    return input;
  }

}
