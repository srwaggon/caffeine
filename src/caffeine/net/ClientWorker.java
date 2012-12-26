package caffeine.net;
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
      //if (server.hasPacket()) {
      server.readPacket();
      //handle(server.readPacket(), game);
      //}

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void handle(Packet packet, Game game) {
    ;
  }

}
