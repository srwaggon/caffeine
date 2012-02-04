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
      while((input = in.readLine()) != null){
        System.out.println(input);
        System.out.println("From Client: " + input);
      }
    }catch (Exception e){ }
    System.out.println(client.getInetAddress().toString() + " disconnected.");

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
