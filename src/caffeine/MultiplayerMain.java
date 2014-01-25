package caffeine;

import caffeine.net.GameClient;
import caffeine.net.GameServer;

public class MultiplayerMain {
  
  public static void main(String[] args) {
    Caffeine game = new Caffeine();
    GameServer gs = new GameServer(game, 4444);
    game.start();
    gs.start();
    new GameClient("fnar", "mucus", "127.0.0.1", 4444).start();
  }
}
