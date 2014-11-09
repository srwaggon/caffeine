package caffeine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import caffeine.entity.mob.PlayerEntity;
import caffeine.entity.mob.brain.InteractiveBrain;

public class SinglePlayerMain {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    Caffeine caffeine = new Caffeine();
//     caffeine.addEntity(new Mob());
//     caffeine.addEntity(new Mob());

    addPlayer(caffeine);

    executor.submit(caffeine);
  }

  private static void addPlayer(Caffeine caffeine) {
    PlayerEntity player = new PlayerEntity("Fnar");
    player.setLoc(32, 32, 0);
    InputHandler inputHandler = caffeine.getGUI().getInputHandler();
    InteractiveBrain brain = new InteractiveBrain(player, inputHandler);
    player.setBrain(brain);
    caffeine.addEntity(player);
  }
}
