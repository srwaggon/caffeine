package caffeine;

import caffeine.entity.PlayerEntity;
import caffeine.entity.brain.InteractiveBrain;

public class SinglePlayerMain {

  public static void main(String[] args) {
    Caffeine caffeine = new Caffeine();
    // caffeine.addEntity(new Mob());
    // caffeine.addEntity(new Mob());

    addPlayer(caffeine);

    caffeine.start();
  }

  private static void addPlayer(Caffeine caffeine) {
    PlayerEntity player = new PlayerEntity("Fnar");
    InputHandler inputHandler = caffeine.getGUI().getInputHandler();
    InteractiveBrain brain = new InteractiveBrain(player, inputHandler);
    player.setBrain(brain);
    caffeine.addEntity(player);
  }
}
