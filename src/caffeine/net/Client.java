package caffeine.net;

import java.util.HashMap;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.gfx.InputHandler;

public class Client extends Thread {
  protected int id;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Game game;
  protected GUI gui;
  protected Connection server;
  protected InputHandler input;

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).start();
  }

  /* Constructor */
  public Client(String ip, int port) {
    id = (int) (Math.random()*99) + 1;
    game = new Game();
    input = new InputHandler();
    server = new Connection(ip, port);
    gui = new GUI("Caffeine Client");
    gui.addInputListener(input);
  }

  public void run() {

    new ClientWorker(server, game).start();
    server.send("fnar");

    final double nsPerTick = 1000000000.0 / 60;
    long now, lastTime = System.nanoTime();
    double unprocessed = 0;


    while (server.isConnected()) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      while (unprocessed >= 1) {
        game.tick();
        input.tick();
        processInput();
        unprocessed -= 1;
      }

      game.getMap(0).renderBackground(gui.screen);
      game.getMap(0).renderSprites(gui.screen);
      gui.screen.render();

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Host disconnected.");
  }

  public void processInput(){
    if (input.up.isPressed) {
      server.send("# "+id+" M N");
    }
    if (input.right.isPressed) {
      server.send("# "+id+" M E");
    }
    if (input.down.isPressed) {
      server.send("# "+id+" M S");
    }
    if (input.left.isPressed) {
      server.send("# "+id+" M W");
    }
    if (input.jump.clicked) {
      server.send("# "+id+" J");
    }
    if (input.use.clicked) {
      server.send("# "+id+" U");
    }
  }

  public void finalize(){
    server.disconnect();
  }
}
