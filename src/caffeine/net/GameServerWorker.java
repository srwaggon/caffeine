package caffeine.net;

import java.net.Socket;

public class GameServerWorker extends Thread {
  protected static byte numWorkers = 1;
  protected final Connection client;
  protected final GameServer server;

  public GameServerWorker(GameServer _server, Socket _client) {
    server = _server;
    client = new Connection(_client);
  }

  public void disconnect(){
    server.remove(this);
    client.disconnect();
  }


  @Override
  public void run() {
    while (client.isConnected()) {
      if (client.hasNextLine()){
        server.handle(client.nextLine());
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
