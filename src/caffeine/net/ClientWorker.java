package caffeine.net;

import java.net.Socket;

public class ClientWorker extends Thread {
  protected static byte numWorkers = 0;
  protected byte id;
  protected final Connection client;
  protected final GameServer server;



  public ClientWorker(GameServer _server, Socket _client) {
    id = numWorkers++;
    server = _server;
    client = new Connection(_client);
    server.handle("H", id);
  }

  public void disconnect(){
    server.handle("x", id);
    server.remove(this);
    client.disconnect();
  }


  @Override
  public void run() {
    while (client.isConnected()) {
      server.handle(client.read(), id);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) { e.printStackTrace(); }
    }
    disconnect();
  }

  public void send(String msg){
    client.send(msg);
  }

}
