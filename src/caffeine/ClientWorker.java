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

  ClientWorker(Socket client) {
    this.client = client;
    System.out.println("" + client.getInetAddress().toString() + ":" + client.getPort());
  }

  public void run(){
    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }
    
    Map m = Server.instance().world().get(0); 
    out.println(m.toString());
    
    String line;
    while(!client.isClosed()){
      // Relay world state
      
      
      
      for(Entity e : m.entities()){
        out.println(e.toString());
      }
      
      
      // Receive input
      
      // Handle input
    }
  }

  public void stop(){
    try {
      out.println("eot");
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
