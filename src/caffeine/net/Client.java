package caffeine.net;

import java.util.HashMap;

import caffeine.entity.Entity;
import caffeine.gfx.GUI;

public class Client {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected GUI gui;
  protected Connection host;
  protected NetworkInputHandler input;

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).run();
  }

  /* Constructor */
  public Client(String ip, int port) {
    host = new Connection(ip, port);
    input = new NetworkInputHandler(host);
    gui = new GUI("Caffeine Client");
    gui.addInputListener(input);
  }

  public void run() {

    while (host.isConnected()) {
      handle(host.read());
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        
      }
      host.disconnect();
    }
  }
  

  public void handle(String msg) {
    //System.out.println(msg);
    host.send("Hey.");
  }
  
  public void finalize(){
    host.disconnect();
  }
}
