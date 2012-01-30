package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientWorker implements Runnable {
  private Socket client;

  ClientWorker(Socket client) {
    this.client = client;
  }

  public void run(){
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }

    while(true){
      //try{
      //Send data back to client
      line = Server.GAME.world().get(0).toString();
      out.println(line);
      /* }catch (IOException e) {
        System.out.println("Read failed");
        System.exit(-1);
      }
       */
    }
  }
}