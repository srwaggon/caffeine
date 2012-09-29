package caffeine.net;

import java.net.Socket;

import caffeine.entity.Entity;
import caffeine.entity.Player;

public class ClientWorker extends Thread {
  protected static byte numWorkers = 0;
  protected byte id;
  protected final Connection client;
  protected final GameServer server;
  protected final Player entity;



  public ClientWorker(GameServer _server, Socket _client) {
    id = numWorkers++;
    server = _server;
    client = new Connection(_client);
    client.send(server.getGame().getDefaultMap().toString());
    entity = new Player();
    entity.init(server.getGame().getDefaultMap());
    server.getGame().getWorld().addEntity(entity);
  }

  public Entity getEntity(){
    return entity;
  }

  public void disconnect(){
    entity.remove();
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
