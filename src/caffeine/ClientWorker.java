package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import caffeine.entity.Entity;
import caffeine.world.Map;

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
      Map m = Server.instance().world().get(0); 
      out.println(m.toString());
      /*
      for(Entity entity : m.entities()){
        out.println(entity.toString());
      }
      */
    }
    // terminate connection : Send end of transmission
    out.println("EOT");
  }

  public void stop(){
    running = false;
  }

}
