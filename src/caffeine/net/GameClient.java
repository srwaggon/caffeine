package caffeine.net;

import java.util.HashMap;

import link.packet.Packet;
import pixl.Frame;
import caffeine.Caffeine;
import caffeine.InputHandler;
import caffeine.entity.Entity;
import caffeine.net.packet.CaffeineCode;
import caffeine.net.packet.CaffeinePacket;
import caffeine.net.packet.JumpPacket;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MovePacket;
import caffeine.net.packet.UseRightPacket;
import caffeine.world.Dir;

public class GameClient extends link.Client {
  protected final String USERNAME;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Caffeine game = new Caffeine();
  private final Frame frame = new Frame("Caffeine Client");
  protected InputHandler input = new InputHandler();

  public static void main(String[] args) {
    new GameClient("fnar", "mucus", "127.0.0.1", 4444).start();
  }

  /* Constructor */
  public GameClient(String username, String password, String ip, int port) {
    super(ip, port);
    frame.setInputListener(input);
    USERNAME = username;
    packetListener.send(new LoginPacket(username, password));
    frame.addRenderable(game);
  }

  @Override
  public void start() {
    frame.start();
    super.start();
  }

  @Override
  public void run() {

    final double nsPerTick = 1000000000.0 / 60;
    long now, lastTime = System.nanoTime();
    double unprocessed = 0;

    while (true) {
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

  }

  public void processInput() {
    if (input.up.isPressed && !input.down.isPressed) {
      packetListener.send(new MovePacket(USERNAME, Dir.N));
    }
    if (input.right.isPressed && !input.left.isPressed) {
      packetListener.send(new MovePacket(USERNAME, Dir.E));
    }
    if (input.down.isPressed && !input.up.isPressed) {
      packetListener.send(new MovePacket(USERNAME, Dir.S));
    }
    if (input.left.isPressed && !input.right.isPressed) {
      packetListener.send(new MovePacket(USERNAME, Dir.W));
    }
    if (input.jump.isClicked) {
      packetListener.send(new JumpPacket(USERNAME));
    }
    if (input.use.isClicked) {
      packetListener.send(new UseRightPacket(USERNAME));
    }
  }

  @Override
  public void handlePacket(Packet packet) {
    int packetCode = packet.getCode();
    Class packetClass = CaffeineCode.get(packetCode).getPacketClass();
    ((CaffeinePacket) packetClass.cast(packet)).apply(game);
  }
}
