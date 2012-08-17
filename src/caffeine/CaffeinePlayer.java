package caffeine;

import caffeine.entity.brain.PlayerBrain;
import caffeine.net.ClientWorker;

public class CaffeinePlayer extends Player {

  protected Caffeine game;

  public CaffeinePlayer(Caffeine game) {
    super(game);
    this.game = game;
    playerEntity = PlayerBrain.embody(game.getWorld());
    input = ((PlayerBrain) playerEntity.getBrain()).getInputHandler();
  }

  public CaffeinePlayer(ClientWorker cw) {
    super(cw);
  }

}
