package caffeine.net;

import java.util.HashMap;

import pixl.Frame;
import pixl.InputHandler;
import caffeine.Caffeine;
import caffeine.entity.Entity;
import caffeine.entity.Mob;
import caffeine.net.packet.ActionPacket;
import caffeine.net.packet.ErrorPacket;
import caffeine.net.packet.FatalErrorPacket;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MapPacket;
import caffeine.net.packet.MovePacket;
import caffeine.net.packet.Packet;
import caffeine.world.Dir;

public class Client extends link.Client {
  protected final String USERNAME;
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  protected Caffeine game = new Caffeine();
  private final Frame frame = new Frame("Caffeine Client");
  protected InputHandler input = new InputHandler();
  
  public static void main(String[] args) {
    new Client("fnar", "mucus", "127.0.0.1", 4444).start();
  }
  
  /* Constructor */
  public Client(String username, String password, String ip, int port) {
    super(ip, port);
    frame.addInputHandler(input);
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
    if (input.up.isPressed && !input.down.isPressed)
      packetListener.send(new MovePacket(USERNAME, Dir.N));
    if (input.right.isPressed && !input.left.isPressed)
      packetListener.send(new MovePacket(USERNAME, Dir.E));
    if (input.down.isPressed && !input.up.isPressed)
      packetListener.send(new MovePacket(USERNAME, Dir.S));
    if (input.left.isPressed && !input.right.isPressed)
      packetListener.send(new MovePacket(USERNAME, Dir.W));
    if (input.jump.isClicked)
      packetListener.send(new ActionPacket(Packet.Code.JUMP, USERNAME));
    if (input.use.isClicked)
      packetListener.send(new ActionPacket(Packet.Code.USERIGHT, USERNAME));
  }
  
  public void handle(Packet packet) {
    switch (packet.getCode()) {
      case ERROR:
        ErrorPacket ep = (ErrorPacket) packet;
        System.out.println(ep.toString());
        break;
      case FATAL_ERROR:
        FatalErrorPacket fep = (FatalErrorPacket) packet;
        System.out.println(fep.getMessage());
        System.exit(fep.getErrorCode());
        break;
      case MAP:
        MapPacket mp = (MapPacket) packet;
        game.addMap(mp.MAP);
        break;
      case MOVE:
        MovePacket move = (MovePacket) packet;
        game.getEntity(move.USERNAME).move(move.DIR);
        break;
      case JUMP:
        ActionPacket jump = (ActionPacket) packet;
        game.getEntity(jump.USERNAME).jump();
        break;
      case USERIGHT:
        ActionPacket use = (ActionPacket) packet;
        Mob m = ((Mob) game.getEntity(use.USERNAME));
        m.useRightHand();
      default:
        break;
    }
  }
}
