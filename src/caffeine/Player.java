package caffeine;

import caffeine.entity.Entity;
import caffeine.gfx.InputHandler;
import caffeine.net.ClientWorker;

public class Player {
  protected boolean networkPlayer = false;
  protected ClientWorker client;
  protected Entity entity;
  protected InputHandler input = new InputHandler();
  protected Caffeine game;

  public Player(Caffeine game) {
    this.game = game;
  }

  public Player(ClientWorker cw) {
    this(cw.game());
    client = cw;
    input = client.getInputHandler();
  }

  public Entity getEntity() {
    return entity;
  }

  public InputHandler getInputHandler() {
    return input;
  }
}