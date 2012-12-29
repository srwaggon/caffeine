package caffeine.net;
import java.io.IOException;

import caffeine.Game;
import caffeine.entity.Mob;
import caffeine.net.packet.ActionPacket;
import caffeine.net.packet.MapPacket;
import caffeine.net.packet.MovePacket;
import caffeine.net.packet.Packet;

public class ClientWorker extends Thread {

  protected Connection server;
  protected Game game;

  public ClientWorker(Connection server, Game game){
    this.server = server;
    this.game = game;
  }

  public void run() {
    while (server.isConnected()) {

      try {
        handle(server.readPacket(), game);
        Thread.sleep(2);
      } catch (IOException ioe) {
        server.disconnect();
        ioe.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void handle(Packet packet, Game game) {
    switch (packet.getCode()) {
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
