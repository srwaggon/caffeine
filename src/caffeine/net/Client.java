package caffeine.net;

import java.util.HashMap;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.gfx.InputHandler;
import caffeine.net.msg.MsgHandler;

public class Client {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Game game;
  protected GUI gui;
  protected Connection host;
  protected InputHandler input;

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).run();
  }

  /* Constructor */
  public Client(String ip, int port) {
    host = new Connection(ip, port);
    input = new InputHandler();
    gui = new GUI("Caffeine Client");
    gui.addInputListener(input);
  }

  public void run() {

    while (host.isConnected()) {

      handle(host.read());

      input.tick();

      host.send("M E");

      game.getDefaultMap().renderBackground(gui.screen);
      game.getDefaultMap().renderSprites(gui.screen);
      gui.screen.render();

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  public void handle(String msg) {
    System.out.println(msg);
    MsgHandler.handle(msg, game);
  }

  public void finalize(){
    host.disconnect();
  }
}
