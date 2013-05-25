package caffeine.net;

import java.util.HashMap;

import pixl.Frame;
import pixl.InputHandler;
import caffeine.Caffeine;
import caffeine.entity.Entity;
import caffeine.net.packet.ActionPacket;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MovePacket;
import caffeine.net.packet.Packet;
import caffeine.world.Dir;

public class Client extends Thread {
  protected final String USERNAME;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Caffeine game = new Caffeine();
  protected Connection server;
  private final Frame frame = new Frame("Caffeine Client");
  protected InputHandler input = new InputHandler();
  
  public static void main(String[] args) {
    new Client("fnar", "mucus", "127.0.0.1", 4444).start();
  }
  
  /* Constructor */
  public Client(String username, String password, String ip, int port) {
    frame.addInputHandler(input);
    USERNAME = username;
    server = new Connection(ip, port);
    server.send(new LoginPacket(username, password));
    new ClientWorker(server, game).start();
    frame.addRenderable(game);
    frame.start();
  }
  
  @Override
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
    if (input.jump.isClicked)
      server.send(new ActionPacket(Packet.Code.JUMP, USERNAME));
    if (input.use.isClicked)
      server.send(new ActionPacket(Packet.Code.USERIGHT, USERNAME));
  }
  
  @Override
  public void finalize() {
    server.disconnect();
  }
}
