package caffeine;

import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.RandomBrain;
import caffeine.entity.brain.RightBrain;
import caffeine.view.Camera;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the mechanics, but are broken down into smaller components.
 * @author Fnar
 */
public final class CaffeineGame {
  private static InteractionHandler interactionHandler= new InteractionHandler();
  private Clock clock = new Clock();
  public final static CaffeineGame GAME = new CaffeineGame();
  GUI gui = new GUI();
  World world = new World(new Map());


  /**
   * Main method
   * @param args
   */
  public static void main(String args[]){
    CaffeineGame.instance();
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);


    Actor a = new Actor(l);
    a.brain(new RandomBrain());

    a = new Actor(l);
    a.brain(new LeftBrain());

    a = new Actor(l);
    a.brain(new RightBrain());

    camera().focusOn(adam);

  }

  private CaffeineGame(){
    clock.add(new TimerTask(){
      public void run(){
        world.tick();
        gui.repaint();
      }
    });
    clock.start();
  }

  public static Camera camera(){
    return GAME.gui.getContentPane().getCamera();
  }

  public List<Entity> entities(int mapID){
    return world.get(mapID).entities();
  }

  public static CaffeineGame instance(){
    return GAME;
  }

  public World world(){
    return world;
  }

  public static InteractionHandler interactionHandler() {
    return interactionHandler;
  }
}

