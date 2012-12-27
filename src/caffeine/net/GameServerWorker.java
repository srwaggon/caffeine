package caffeine.net;

import java.io.IOException;

import caffeine.net.accounts.PlayerAccount;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.Packet;


public class GameServerWorker extends Thread {
  protected static byte numWorkers = 1;
  protected final Connection client;
  protected final GameServer server;
  protected PlayerAccount player;

  public GameServerWorker(GameServer server, Connection client) throws IOException {
    this.server = server;
    this.client = client;

    // Load the connecting player's account.
    LoginPacket login = (LoginPacket) client.readPacket();
    player = PlayerAccount.loadPlayer(login.USERNAME);

    if (player.authenticate(login.PASSWORD)) {
      server.addWorker(this);
    } else {
      client.send("authorization failed.");
      client.disconnect();
    }
  }

  public void disconnect(){
    server.removeWorker(this);
    client.disconnect();
  }

  public PlayerAccount getPlayer() {
    return player;
  }


  @Override
  public void run() {
    while (client.isConnected()) {
      try {
        server.handle(player, client.readPacket());
        Thread.sleep(2);

      } catch (IOException e) {
        client.disconnect();
        e.printStackTrace();
      } catch (InterruptedException e) {

        e.printStackTrace();
      }
    }
    disconnect();
  }

  public void send(Packet packet){
    client.send(packet);
  }

}
