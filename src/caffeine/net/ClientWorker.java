package caffeine.net;

import java.net.Socket;

public class ClientWorker extends Thread {
  protected static byte numWorkers = 0;
  protected byte id;
  protected final GameServer server;
  protected final Connection client;

  public ClientWorker(GameServer _server, Socket _client) {
    id = numWorkers++;
    server = _server;
    client = new Connection(_client);
    client.send(server.getGame().getDefaultMap().toString());
  }


  @Override
  public void run() {
    while (client.isConnected()) {
      server.handle(client.read(), id);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) { e.printStackTrace(); }
    }
    server.remove(this);
    client.disconnect();
  }

  public void send(String msg){
    client.send(msg);
  }

}
