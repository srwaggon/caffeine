package caffeine.net;

import java.util.HashMap;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.gfx.InputHandler;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MovePacket;
import caffeine.world.Dir;

public class Client extends Thread {
  protected final String USERNAME;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Game game = new Game();
  protected GUI gui = new GUI("Caffeine Client");
  protected Connection server;
  protected InputHandler input = new InputHandler();

  public static void main(String[] args) {
    new Client("fnar", "mucus", "127.0.0.1", 4444).start();
  }

  /* Constructor */
  public Client(String username, String password, String ip, int port) {
    USERNAME = username;
    server = new Connection(ip, port);
    gui.addInputListener(input);
    server.send(new LoginPacket(username, password));
    new ClientWorker(server, game).start();
  }

  public void run() {

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
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.exit(0);
  }

  public void processInput() {
    if (input.up.isPressed && !input.down.isPressed)
      server.send(new MovePacket(USERNAME, Dir.N));
    if (input.right.isPressed && !input.left.isPressed)
      server.send(new MovePacket(USERNAME, Dir.E));
    if (input.down.isPressed && !input.up.isPressed)
      server.send(new MovePacket(USERNAME, Dir.S));
    if (input.left.isPressed && !input.right.isPressed)
      server.send(new MovePacket(USERNAME, Dir.W));

    /*
    if (input.jump.clicked)
      server.send("# " + ID + " J");
    if (input.use.clicked)
      server.send("# " + ID + " U");
     */
  }

  public void finalize() {
    server.disconnect();
  }
}
