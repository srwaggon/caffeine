package caffeine.net;

import java.util.HashMap;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.gfx.GUI;
import caffeine.gfx.InputHandler;
import caffeine.net.packet.LoginPacket;

public class Client extends Thread {
  public final String ID = "0";
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Game game = new Game();
  protected GUI gui = new GUI("Caffeine Client");
  protected Connection server;
  protected InputHandler input = new InputHandler();

  public static void main(String[] args) {
    new Client("127.0.0.1", 4444).start();
  }

  /* Constructor */
  public Client(String ip, int port) {
    server = new Connection(ip, port);
    gui.addInputListener(input);
    server.send(new LoginPacket("fnar", "mucus"));
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
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Host disconnected.");
    System.exit(0);
  }

  public void processInput() {
    if (input.up.isPressed)
      server.send("# " + ID + " M N");
    if (input.right.isPressed)
      server.send("# " + ID + " M E");
    if (input.down.isPressed)
      server.send("# " + ID + " M S");
    if (input.left.isPressed)
      server.send("# " + ID + " M W");
    if (input.jump.clicked)
      server.send("# " + ID + " J");
    if (input.use.clicked)
      server.send("# " + ID + " U");
  }

  public void finalize() {
    server.disconnect();
  }
}
