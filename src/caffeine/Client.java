package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Map;


public class Client implements Runnable{
  /* Engine Fields */
  protected InteractionHandler interactions = new InteractionHandler();
  private Map map = new Map();
  protected GUI gui = null;
  /* Networking Fields */
  private Socket socket = null;
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
      socket = new Socket(host, 4444);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

  @Override
  public void run() {
    boolean running = true;
    while(running){
      if(lineParser.hasNext()){
        String line = lineParser.next();
        System.out.println("Received from server:" + line + "!!!");
        if(line.equals("EOT")){
          System.out.println("Server disconnected.");
          running = false;
        } else if (line.equals("map")){
          map = new Map(lineParser.nextLine());
          gui.getContentPane().setCurrentMap(map);
          gui.repaint();
        }
      }
    }
    System.exit(0);
  }
}
