package caffeine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import caffeine.world.Map;
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
    final Server g = Server.instance();
    Map map = g.world().get(0);
    
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l, g.interactionHandler());
    map.add(adam);
    
    Actor a = Actor.create(l);
    a.brain(new RandomBrain());
    map.add(a);
    
    a = Actor.create(l);
    a.brain(new LeftBrain());
    map.add(a);

    a = Actor.create(l);
    a.brain(new RightBrain());
    map.add(a);
    
    Screen s = g.gui().getContentPane();
    s.setCurrentMap(g.world().get(0));
    s.camera().focusOn(adam);

    g.listenSocket();
  }

  
  
  
  
  
  /* CONSTRUCTORS */
  private Server(){
    initSockets();
    createGUI();
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

  public List<ClientWorker> clients(){
    return clients;
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
  private void createGUI(){
    if (gui == null) {
      gui = new GUI("Caffeine Server", interactionHandler);
      clock.add(new TimerTask(){
        public void run(){
          gui.repaint();
        }
      });
      
      WindowListener winListener = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          for (ClientWorker w : clients) {
            w.stop();
          }
          System.exit(0);
        }
      };
      gui.addWindowListener(winListener);
    }
  }


  private void initSockets(){
    try{
      server = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port 4444");
      System.exit(-1);
    }
  }

  private void listenSocket(){
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

