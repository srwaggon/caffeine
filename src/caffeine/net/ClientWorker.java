package caffeine.net;
import caffeine.Game;
import caffeine.net.msg.MsgHandler;

public class ClientWorker extends Thread {

  protected Connection server;
  protected Game game;

  public ClientWorker(Connection server, Game game){
    this.server = server;
    this.game = game;
  }

  public void run() {
    while (server.isConnected()) {

      if (server.hasNextLine()) {
        MsgHandler.handle(server.nextLine(), game);
      }
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
