package caffeine.net;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import link.Server;
import link.packet.Packet;
import caffeine.Caffeine;
import caffeine.entity.Entity;
import caffeine.entity.PlayerEntity;
import caffeine.net.accounts.PlayerAccount;
import caffeine.net.packet.CaffeineCode;
import caffeine.net.packet.CaffeinePacket;
import caffeine.net.packet.ErrorPacket;
import caffeine.net.packet.LoginPacket;
import caffeine.net.packet.MapPacket;
import caffeine.world.tile.Tile;

public class GameServer extends Server implements MapListener {
  private final Caffeine game;
  private final HashMap<String, GameServerWorker> clients = new HashMap<String, GameServerWorker>();
  
  public static void main(String args[]) {
    Caffeine game = new Caffeine();
    GameServer gs = new GameServer(game, 4444);
    game.start();
    gs.start();
  }
  
  public GameServer(Caffeine game, int port) {
    super(port);
    this.game = game;
    game.getMap(0).addMapListener(this);
  }
  
  /**
   * Receives a socket representing a client connection and wraps it in a
   * connection before attempting to authenticate it as a valid login packet. If
   * the login is successful, a GameServerWorker is requested and assigned to
   * handle incoming data from the client.
   * 
   * @param socket
   */
  @Override
  public void acceptConnection(Socket socket) {
    System.out.println(socket.getInetAddress().toString() + " connecting.");
    Connection client = new Connection(socket);
    
    Packet packet = null;
    try {
      packet = client.readPacket();
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return;
    }
    
    // authenticate primary packet is login or new account.
    if (packet.getCode() != CaffeineCode.LOGIN.ordinal()) {
      client.send(new ErrorPacket("Bad login."));
      return;
    }
    
    // check player username / password authenticity.
    LoginPacket loginPacket = (LoginPacket) packet;
    PlayerAccount account = PlayerAccount.loadPlayer(loginPacket.USERNAME);
    if (!account.authenticate(loginPacket.PASSWORD)) {
      client.send(new ErrorPacket("Bad username/password."));
      return;
    }
    
    // check if not already online.
    if (clients.get(account.getUsername()) != null) {
      client.send(new ErrorPacket("Account already connected."));
      return;
    }
    
    // accept connection
    GameServerWorker worker = requestWorker();
    worker.handleConnection(client, account);
    assignClient(worker, account);
    worker.start();
  }
  
  public synchronized void handle(PlayerAccount player, Packet packet) {
    int packetCode = packet.getCode();
    Class packetClass = CaffeineCode.get(packetCode).getClass();
    ((CaffeinePacket) packetClass.cast(packet)).apply(game);
    System.out.println("Received" + packet);
    
    // switch (packet.getCode()) {
    // case MOVE:
    // MovePacket move = (MovePacket) packet;
    // game.getEntity(move.USERNAME).move(move.DIR);
    // break;
    // case JUMP:
    // ActionPacket jump = (ActionPacket) packet;
    // game.getEntity(jump.USERNAME).jump();
    // break;
    // case USERIGHT:
    // ActionPacket use = (ActionPacket) packet;
    // Mob m = ((Mob) game.getEntity(use.USERNAME));
    // m.useRightHand();
    // default:
    // break;
    // }
    broadcast(packet);
  }
  
  public void broadcast(Packet packet) {
    for (GameServerWorker client : clients.values()) {
      client.send(packet);
    }
  }
  
  public GameServerWorker requestWorker() {
    GameServerWorker worker = new GameServerWorker(this);
    return worker;
  }
  
  public synchronized void assignClient(GameServerWorker worker,
      PlayerAccount account) {
    
    // Add account's avatar to the game.
    PlayerEntity entity = account.getEntity();
    System.out.println(entity.ID + " added to game.");
    game.addEntity(entity, entity.getMapID());
    worker.client.send(new MapPacket(entity.getMap()));
    
    // track worker by account
    clients.put(account.getUsername(), worker);
  }
  
  public synchronized void removeAccount(PlayerAccount account) {
    PlayerAccount.savePlayer(account);
    account.getEntity().remove();
    clients.remove(account.getUsername());
  }
  
  @Override
  public void onAddEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }
  
  @Override
  public void onRemoveEntity(Entity entity) {
    broadcast(new MapPacket(entity.getMap()));
  }
  
  @Override
  public void onTileChange(Tile tile) {
    ;
  }
}
