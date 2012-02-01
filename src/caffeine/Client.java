package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Map;


public class Client implements Runnable{
  /* Engine Fields */
  protected InteractionHandler interactions = new InteractionHandler();
  private Map map = new Map();
  protected GUI gui = null;
  /* Networking Fields */
  private Socket connection = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private Scanner lineParser = null;


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
      lineParser = new Scanner(in);
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
    while (!connection.isClosed()) {
      gui.repaint();
      if (lineParser.hasNext()){
        // Read the line, and report it.
        String line = lineParser.next();
        
        // Handle the input
        if (line.equals("eot")){
          try {
            connection.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        } else if (line.equals("map")) {
          map = new Map(lineParser.nextLine());
          gui.getContentPane().setCurrentMap(map);
        }
      }
    }
    System.out.println("Server disconnected.");
    System.exit(0);
  }
}
