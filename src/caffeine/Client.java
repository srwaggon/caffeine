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
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.Map;


public class Client implements Runnable{
  /* Engine Fields */
  protected InteractionHandler interactions = new InteractionHandler();
  private Map map = new Map();
  private HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected GUI gui = null;
  /* Networking Fields */
  private Socket connection = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private Scanner scans = null;


  /* MAIN METHOD */
  public static void main(String[] args){
    Client c = new Client();
  }

  /* Constructor */
  public Client(){
    connectSocket();
    gui = new GUI("Caffeine Client", interactions);
    run();
  }

  public void connectSocket(){
    //Create socket connection
    String host = "127.0.0.1";
    try{
      System.out.println("Connecting to 127.0.0.1 ...");
      connection = new Socket(host, 4444);
      out = new PrintWriter(connection.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      scans = new Scanner(in);
    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + host);
      System.exit(1);
    } catch  (IOException e) {
      System.out.println("No I/O");
      System.exit(1);
    }
    System.out.println("Connection established.");
  }

  public void run() {
    /* While connected */
    while (!connection.isClosed()) {
      /* Read from in-stream and process it */
      String line = scans.nextLine();
      System.out.println(line);
      processServerResponse(line);
    }
    System.out.println("Server disconnected.");
  }


  public void processServerResponse(String response){
    Scanner lineParser = new Scanner(response);
    String next;

    while (lineParser.hasNext()){
      next = lineParser.next();

      /* Handle the input */
      if (next.equals("eot")){
        /* End of transmission */
        try {
          connection.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;

      } else if (next.equals("map")) {
        /* Update map */
        map = new Map(lineParser.nextLine());
        gui.getContentPane().setCurrentMap(map);

      } else if (next.equals("entity")) {
        /* Update entity */
        int entityID = lineParser.nextInt();
        if (entities.containsKey(entityID)) {

          int mapID = lineParser.nextInt();
          int x = lineParser.nextInt();
          int y = lineParser.nextInt();

          Location l = entities.get(entityID).loc();
          l.set(mapID, x, y);

        } else {
          Entity e = Entity.newEntity(entityID + " " + lineParser.nextLine());
          map.add(e);
          entities.put(entityID, e);
        }
      }
      gui.repaint();
    }
  }
}
