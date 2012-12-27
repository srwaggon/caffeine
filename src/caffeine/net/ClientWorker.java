package caffeine.net;
import java.io.IOException;

import caffeine.Game;
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
    ;
  }

}
