package caffeine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import caffeine.net.GameClient;
import caffeine.net.GameServer;

public class MultiplayerMain {
  
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Caffeine game = new Caffeine();
    GameServer gs = new GameServer(game, 4444);
    executor.submit(game);
    gs.start();
    new GameClient("fnar", "mucus", "127.0.0.1", 4444).start();
  }
}
