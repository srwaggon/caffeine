package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
  private Socket socket = null;
  private PrintWriter out = null;
  private BufferedReader in = null;

  public void listenSocket(){
    //Create socket connection
    try{
      System.out.println("Connecting to 127.0.0.1 ...");
      socket = new Socket("127.0.0.1", 4444);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (UnknownHostException e) {
      System.out.println("Unknown host: kq6py.eng");
      System.exit(1);
    } catch  (IOException e) {
      System.out.println("No I/O");
      System.exit(1);
    }
    System.out.println("Connection established.");
  }

  public static void main(String[] args){
    Client c = new Client();
    c.listenSocket();
  }

}
