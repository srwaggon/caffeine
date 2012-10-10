package caffeine.net;

import caffeine.Player;


public class GameServerWorker extends Thread {
  protected static byte numWorkers = 1;
  protected final Connection client;
  protected final GameServer server;
  protected final Player player;

  public GameServerWorker(GameServer server, Connection client, Player player) {
    this.server = server;
    this.client = client;
    this.player = player;
  }

  public void disconnect(){
    server.remove(this);
    client.disconnect();
  }


  @Override
  public void run() {
    while (client.isConnected()) {
      if (client.hasLine()){
        server.handle(client.readLine());
      }

      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    disconnect();
  }

  public void send(String msg){
    client.send(msg);
  }

}
