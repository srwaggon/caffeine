package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import caffeine.view.GFX;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.World;

/**
 * A game is the mover and shaker of the engine and represents a single play.
 * Games handle everything, from graphics to the heartbeat, as well as the mechanics, but are broken down into smaller components.
 * @author Fnar
 */
public final class Server {
  private Clock clock = new Clock(); // Heartbeat.  Handles game cycles and tick alerts.
  final static InteractionHandler interactionHandler = new InteractionHandler(); // Handles input from Keyboard and Mouse
  private static final GFX GFX = new GFX(); // handles graphics
  protected World world = new World(); // handles entities and interactions
  public final static Server GAME = new Server();  // Instance

  /* Networking Objects */
  ServerSocket server = null;
  Socket client = null;
  BufferedReader in = null;
  PrintWriter out = null;
  String line;

  public void listenSocket(){

    try{
      server = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port 4444");
      System.exit(-1);
    }

    try{
      client = server.accept();
    } catch (IOException e) {
      System.out.println("Accept failed: 4444");
      System.exit(-1);
    }

    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("Accept failed: 4444");
      System.exit(-1);
    }

    while(true){
      try{
        line = in.readLine();
        //Send data back to client
        out.println(line);
      } catch (IOException e) {
        System.out.println("Read failed");
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




  /**
   * Main method
   * @param args
   */
  public static void main(String args[]){
    Server g = Server.game();
    Location l = new Location(0, 48, 48);
    Player adam = new Player(l);

    g.gfx().camera().focusOn(adam);


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
        GFX.tick();
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

