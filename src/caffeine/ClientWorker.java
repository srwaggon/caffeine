package caffeine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import caffeine.entity.Entity;
import caffeine.world.Map;

class ClientWorker extends Thread {
  private Socket client;
  BufferedReader in = null;
  PrintWriter out = null;
  
  ClientWorker(Socket client) {
    this.client = client;
    System.out.println("" + client.getInetAddress().toString() + ":" + client.getPort() + " connecting");
  }
  
  public void run(){
    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }
    
    try {
      String input;
      String response = "";
      out.println("Hello client");
      /* while connected with client */
      while((input = in.readLine()) != null){
        Map m = Server.instance().world().get(0);
        for (Entity e : m.entities()){
          response += e.toString() + " ";
        }
        out.println(response);
        sleep(10);
      }
      System.out.println(client.getInetAddress().toString() + " disconnected.");
    } catch (InterruptedException e) {
      
    } catch (Exception e){
      
    }
    
  }
}
