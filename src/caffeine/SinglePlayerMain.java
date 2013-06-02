package caffeine;

import pixl.Frame;
import caffeine.entity.Mob;
import caffeine.entity.PlayerEntity;
import caffeine.entity.brain.InteractiveBrain;

public class SinglePlayerMain {
  
  public static void main(String[] args) {
    Caffeine caffeine = new Caffeine();
    caffeine.addEntity(new Mob());
    caffeine.addEntity(new Mob());
    PlayerEntity player = new PlayerEntity("Fnar");
    caffeine.addEntity(player);
    Frame gui = new Frame("Caf");
    gui.addRenderable(caffeine);
    player.setBrain(new InteractiveBrain(gui.getInputHandler(), player));
    
    caffeine.start();
    gui.start();
  }
}
