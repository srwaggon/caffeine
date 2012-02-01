package caffeine;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
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
  private final static Server INSTANCE = new Server();  // Instance
  private World world = new World(); // Space
  private Clock clock = new Clock(); // Time
  private InteractionHandler interactionHandler = new InteractionHandler(); // Input
  private GUI gui = null;


  /* Networking Fields */
  private ServerSocket server = null;
  private PrintWriter out = null;
  private List<ClientWorker> clients = new ArrayList<ClientWorker>();

  /* Main method */
  public static void main(String args[]){
    Server g = Server.instance();

    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);

    Actor a = Actor.create(l);
    a.brain(new RandomBrain());

    a = Actor.create(l);
    a.brain(new LeftBrain());

    a = Actor.create(l);
    a.brain(new RightBrain());

    g.createGUI();
    Screen s = g.gui().getContentPane();
    s.setCurrentMap(g.world().get(0));
    s.camera().focusOn(adam);

    g.listenSocket();
  }

  /* CONSTRUCTORS */
  private Server(){
    initSockets();
    clock.add(new TimerTask(){
      public void run(){
        world.tick();
      }
    });
    clock.start();
  }

  /* ACCESSORS */
  public List<Entity> entities(int mapID){
    return world.get(mapID).entities();
  }

  public GUI gui(){
    return gui;
  }

  public static Server instance(){
    return INSTANCE;
  }

  public InteractionHandler interactionHandler() {
    return interactionHandler;
  }

  public World world(){
    return world;
  }

  /* HELPERS */
  public void createGUI(){
    if(gui == null){
      gui = new GUI("Caffeine Server", interactionHandler);
      clock.add(new TimerTask(){
        public void run(){
          gui.repaint();
        }
      });
    }
  }

  public void initSockets(){
    try{
      server = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port 4444");
      System.exit(-1);
    }
  }

  public void listenSocket(){
    while(true){
      ClientWorker w;
      try{
        w = new ClientWorker(server.accept());
        Thread t = new Thread(w);
        clients.add(w);
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
      for(ClientWorker w : clients){
        w.stop();
      }
      out.close();
      server.close();
      super.finalize();
    } catch (IOException e) {
      System.out.println("Could not close.");
      System.exit(-1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}

