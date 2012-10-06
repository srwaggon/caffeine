package caffeine.net;

import java.util.HashMap;
import java.util.Scanner;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.gfx.GUI;
import caffeine.gfx.InputHandler;
import caffeine.net.msg.MsgHandler;

public class Client implements Runnable {
  protected int id;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Game game;
  protected GUI gui;
  protected Connection host;
  protected InputHandler input;

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).start();
  }

  /* Constructor */
  public Client(String ip, int port) {
    id = (int) (Math.random()*99) + 1;
    game = new Game();
    input = new InputHandler();
    host = new Connection(ip, port);
    gui = new GUI("Caffeine Client");
    gui.addInputListener(input);
  }

  public void start(){
    host.send(new Player(id).toString());
    new Thread(this).run();
  }

  public void run() {
    Scanner in = host.getScanner();
    while (host.isConnected()) {
      input.tick();
      processInput();
      if (in.hasNextLine()) {
        MsgHandler.handle(in.nextLine(), game);
      }
      //game.getMap(0).renderBackground(gui.screen);
      game.getMap(0).renderSprites(gui.screen);
      gui.screen.render();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Host disconnected.");
  }

  public void processInput(){
    if (input.up.isPressed) {
      host.send("# "+id+" M N");
    }
    if (input.left.isPressed) {
      host.send("# "+id+" M E");
    }
    if (input.down.isPressed) {
      host.send("# "+id+" M S");
    }
    if (input.right.isPressed) {
      host.send("# "+id+" M W");
    }
    host.send("\n");
  }

  public void finalize(){
    host.disconnect();
  }
}
