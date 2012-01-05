package caffeine;

import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.RandomBrain;
import caffeine.entity.brain.RightBrain;
import caffeine.view.GFX;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the mechanics, but are broken down into smaller components.
 * @author Fnar
 */
public final class Game {
  private Clock clock = new Clock(); // Heartbeat.  Handles game cycles and tick alerts.
  public final static Game GAME = new Game();  // Instance
  private static final GFX gfx = new GFX(); // handles graphics
  static final InteractionHandler interactionHandler = new InteractionHandler(); // Handles input from Keyboard and Mouse
  protected World world = new World(new Map()); // handles entities and interactions


  /**
   * Main method
   * @param args
   */
  public static void main(String args[]){
    Game g = Game.game();
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);


    Actor a = new Actor(l);
    a.brain(new RandomBrain());

    a = new Actor(l);
    a.brain(new LeftBrain());

    a = new Actor(l);
    a.brain(new RightBrain());
  }

  private Game(){
    clock.add(new TimerTask(){
      public void run(){
        world.tick();
        gfx.tick();
      }
    });
    clock.start();
  }

  public List<Entity> entities(int mapID){
    return world.get(mapID).entities();
  }

  public static Game game(){
    return GAME;
  }


  public GFX gfx() {
    return gfx;
  }

  public static InteractionHandler interactionHandler() {
    return interactionHandler;
  }

  public World world(){
    return world;
  }
}

