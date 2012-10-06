package caffeine.net;

import java.net.Socket;
import java.util.Scanner;

public class ClientWorker extends Thread {
  protected static byte numWorkers = 1;
  protected final Connection client;
  protected final GameServer server;

  public ClientWorker(GameServer _server, Socket _client) {
    server = _server;
    client = new Connection(_client);
  }

  public void disconnect(){
    server.remove(this);
    client.disconnect();
  }


  @Override
  public void run() {
    Scanner in = client.getScanner();
    while (client.isConnected()) {
      if (in.hasNextLine()){
        server.handle(in.nextLine());
      }
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
