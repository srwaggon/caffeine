package caffeine;

import caffeine.net.ClientWorker;

public class CaffeinePlayer extends Player {

  protected Caffeine game;

  public CaffeinePlayer(Caffeine game) {
    super(game);
    this.game = game;
    caffeine.entity.Player playerEntity = new caffeine.entity.Player(input, game.getWorld().getMap(0));
    entity = playerEntity;
  }

  public CaffeinePlayer(ClientWorker cw) {
    super(cw);
  }

}