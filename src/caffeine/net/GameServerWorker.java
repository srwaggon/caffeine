package caffeine.net;

import java.io.IOException;

import caffeine.net.accounts.PlayerAccount;
import caffeine.net.packet.Packet;

public class GameServerWorker extends Thread {
  protected static int numWorkers = 1;
  protected final GameServer server;
  protected Connection client;
  
  protected PlayerAccount account;
  
  public GameServerWorker(GameServer server) {
    this.server = server;
  }
  
  public void handleConnection(Connection client, PlayerAccount account) {
    this.client = client;
    this.account = account;
  }
  
  public PlayerAccount getAccount() {
    return account;
  }
  
  public void send(Packet packet) {
    client.send(packet);
  }
  
  @Override
  public void run() {
    while (client.isConnected()) {
      try {
        server.handle(account, client.readPacket());
        Thread.sleep(2);
      } catch (IOException e) {
        client.disconnect();
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void disconnect() {
    client.disconnect();
    server.removeAccount(account);
  }
  
}
