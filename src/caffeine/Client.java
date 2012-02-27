package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.GUI;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;

public class Client extends Thread {
  /* Engine Fields */
  private World                          realm      = null;
  private final HashMap<Integer, Entity> entities   = new HashMap<Integer, Entity>();
  protected GUI                          gui        = null;
  /* Networking Fields */
  private Socket                         connection = null;
  private PrintWriter                    out        = null;
  private BufferedReader                 in         = null;
  
  /* MAIN METHOD */
  public static void main(String[] args) {
    Client c = new Client();
    c.run();
  }
  
  /* Constructor */
  public Client() {
    connectSocket();
    realm = new World();
    Map map = new Map();
    realm.add(map);
    gui = new GUI(map);
    gui.setTitle("Caffeine Client");
    new Thread(gui).start();
  }
  
  public void connectSocket() {
    // Create socket connection
    String host = "127.0.0.1";
    try {
      System.out.println("Connecting to " + host);
      connection = new Socket(host, 4444);
      out = new PrintWriter(connection.getOutputStream(), true);
      in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + host);
      System.exit(1);
    } catch (IOException e) {
      System.out.println("No I/O");
      System.exit(1);
    }
    System.out.println("Connection established.");
  }
  
  public void run() {
    String input = "";
    String output = "";
    try {
      System.out.println("Connecting to "
          + connection.getInetAddress().toString() + "...");
      out.println("Hello server");
      
      input = in.readLine();
      /* While connected with server */
      while (input != null) {
        
        /* Read from in-stream and process it */
        processServerResponse(input);
        output = "Ok.";
        out.println(output);
        Thread.sleep(10);
        input = in.readLine();
      }
      System.out.println("Server disconnected.");
      
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (Exception e) {
      // e.printStackTrace();
    }
    
  }
  
  public void processServerResponse(String response) {
    Scanner lineParser = new Scanner(response);
    String next;
    
    while (lineParser.hasNext()) {
      next = lineParser.next();
      
      /* Handle the input */
      if (next.equals("eot")) {
        /* End of transmission */
        try {
          connection.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
        
      } else if (next.equals("map")) {
        /* Update map */
        /*
         * map = new Map(lineParser.nextLine());
         * gui.getContentPane().setCurrentMap(map);
         */
      } else if (next.equals("entity")) {
        /* Update entity */
        int entityID = lineParser.nextInt();
        int mapID = lineParser.nextInt();
        int x = lineParser.nextInt();
        int y = lineParser.nextInt();
        
        if (entities.containsKey(entityID)) {
          
          entities.get(entityID).loc().set(mapID, x, y);
          
        } else {
          Entity e = new Entity(new Location(mapID, x, y));
          realm.get(0).add(e);
          entities.put(entityID, e);
        }
      } else {
        lineParser.nextLine();
      }
    }
  }
}
