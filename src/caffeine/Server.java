package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.TimerTask;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.entity.brain.LeftBrain;
import caffeine.entity.brain.RandomBrain;
import caffeine.entity.brain.RightBrain;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.view.Screen;
import caffeine.world.Location;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the mechanics, but are broken down into smaller components.
 * @author Fnar
 */
public final class Server {
  /* Engine Fields */
  protected World world = new World(); // handles entities and interactions
  private Clock clock = new Clock(); // Heartbeat.  Handles game cycles and tick alerts.
  final static InteractionHandler interactionHandler = new InteractionHandler(); // Handles input from Keyboard and Mouse
  private static GUI gui = new GUI(interactionHandler); // handles graphics
  public final static Server GAME = new Server();  // Instance
  /* Networking Fields */
  private ServerSocket server = null;
  private Socket client = null;
  private BufferedReader in = null;
  private PrintWriter out = null;
  private String line;

  public void listenSocket(){
    try{
      server = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port 4444");
      System.exit(-1);
    }
    while(true){
      ClientWorker w;
      try{
        w = new ClientWorker(server.accept());
        Thread t = new Thread(w);
        t.start();
      } catch (IOException e) {
        System.out.println("Accept failed: 4444");
        System.exit(-1);
      }
    }
  }

  protected void finalize(){
    //Clean up
    try{
      in.close();
      out.close();
      server.close();
    } catch (IOException e) {
      System.out.println("Could not close.");
      System.exit(-1);
    }
  }




  /* Main method */
  public static void main(String args[]){
    Server g = Server.game();
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);

    Screen s = gui.getContentPane();
    s.setCurrentMap(g.world().get(0));
    s.camera().focusOn(adam);

    Actor a = Actor.create(l);
    a.brain(new RandomBrain());

    a = Actor.create(l);
    a.brain(new LeftBrain());

    a = Actor.create(l);
    a.brain(new RightBrain());

    g.listenSocket();
  }

  private Server(){
    clock.add(new TimerTask(){
      public void run(){
        world.tick();
        gui.repaint();
      }
    });
    clock.start();
  }

  public List<Entity> entities(int mapID){
    return world.get(mapID).entities();
  }

  public static Server game(){
    return GAME;
  }

  public static InteractionHandler interactionHandler() {
    return interactionHandler;
  }

  public World world(){
    return world;
  }
}

