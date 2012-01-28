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
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the mechanics, but are broken down into smaller components.
 * @author Fnar
 */
public final class Game {
  private Clock clock = new Clock(); // Heartbeat.  Handles game cycles and tick alerts.
  final static InteractionHandler interactionHandler = new InteractionHandler(); // Handles input from Keyboard and Mouse
  private static final GFX GFX = new GFX(); // handles graphics
  protected World world = new World(); // handles entities and interactions
  public final static Game GAME = new Game();  // Instance

  /**
   * Main method
   * @param args
   */
  public static void main(String args[]){
    Game g = Game.game();
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);

    g.gfx().camera().focusOn(adam);


    Actor a = Actor.create(l);
    a.brain(new RandomBrain());

    a = Actor.create(l);
    a.brain(new LeftBrain());

    a = Actor.create(l);
    a.brain(new RightBrain());
  }

  private Game(){
    clock.add(new TimerTask(){
      public void run(){
        world.tick();
        GFX.tick();
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
    return GFX;
  }

  public static InteractionHandler interactionHandler() {
    return interactionHandler;
  }

  public World world(){
    return world;
  }
}

