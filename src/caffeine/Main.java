package caffeine;

import caffeine.entity.Mob;
import caffeine.entity.PlayerEntity;
import caffeine.entity.brain.InteractiveBrain;


public class Main {

  public static void main(String[] args) {
    Game g = new Game();
    g.addEntity(new Mob());
    g.addEntity(new Mob());
    PlayerEntity player = new PlayerEntity("Fnar");
    g.addEntity(player);
    player.setBrain(new InteractiveBrain(g.gui.getInputHandler(), player));
    g.start();
  }
}
