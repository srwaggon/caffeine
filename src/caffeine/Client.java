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


  /* MAIN METHOD */
  public static void main(String[] args){
    Client c = new Client();
  }

  /* Constructor */
  public Client(){
    connectSocket();
    gui = new GUI(map, interactions);
    gui.setTitle("Caffeine Client");
    run();
  }

  public void connectSocket(){
    //Create socket connection
    String host = "127.0.0.1";
    try{
      System.out.println("Connecting to "+ host);
      connection = new Socket(host, 4444);
      out = new PrintWriter(connection.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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

    String input;
    try {
      int i = 0;
      while(i < 1){
        String output = "";
        for(Integer key : interactions.getKeys()) {
          if(interactions.get(key)){
            output += key;
          }
        }
        out.println(output);
      }

      /* While connected */
      while((input = in.readLine()) != null){



        /* Read from in-stream and process it */
      }
      System.out.println("Server disconnected.");

    } catch (IOException e) {
      e.printStackTrace();
    }
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

