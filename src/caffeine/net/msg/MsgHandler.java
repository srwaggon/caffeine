package caffeine.net.msg;

import java.util.Scanner;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.world.Dir;
import caffeine.world.Map;


public class MsgHandler {

  public static boolean handle(String msg, Game game){
    Scanner scanner = new Scanner(msg);

    int id = scanner.nextInt();
    Entity entity = game.getEntity(id);

    String word = scanner.next();
    char c = word.charAt(0);

    if (word.equals("map")){
      scanner.next();
      int w = scanner.nextInt();
      int h = scanner.nextInt();
      int ts = scanner.nextInt();
      String mapData = scanner.next();
      game.addMap(new Map(w, h, ts, mapData));
    }

    if (c == 'M'){
      word = scanner.next();
      c = word.charAt(0);

      Dir dir = Dir.valueOf("" + c);
      System.out.println(dir);
    }

    if (c == 'H'){
      game.addEntity(new Player(id), 0); // TODO: magic #
    }

    if (c == 'x'){
      entity.remove();
    }

    return true;
  }
  
  public static Entity parseEntity(Scanner scanner){
    int id = scanner.nextInt();
    Entity e = new Entity(id);
    e.setX(scanner.nextInt());
    e.setY(scanner.nextInt());
    e.setZ(scanner.nextInt());
    return e;
  }
}
