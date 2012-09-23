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
    client.send(id + " at your service");
  }


  @Override
  public void run() {
    try {
      while (client.isConnected()) {
        server.handle(client.read(), id);
        Thread.sleep(10); 
      }
      server.remove(this);
      client.disconnect();
    } catch (InterruptedException e) { e.printStackTrace(); }
  }
  
  public void send(String msg){
    client.send(msg);
  }

}
