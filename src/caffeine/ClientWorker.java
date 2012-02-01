package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientWorker implements Runnable {
  private Socket client;
  BufferedReader in = null;
  PrintWriter out = null;
  private boolean running = false;

  ClientWorker(Socket client) {
    this.client = client;
  }

  public void run(){
    running = true;
    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }
    String line;
    while(running){
      line = Server.instance().world().get(0).toString();
      out.println(line);
    }
    // terminate connection : Send end of transmission
    out.println("EOT");
  }

  public void stop(){
    running = false;
  }

}
