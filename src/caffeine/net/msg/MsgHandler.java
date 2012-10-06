package caffeine.net.msg;

import java.util.Scanner;

import caffeine.Game;
import caffeine.entity.Entity;
import caffeine.world.Dir;
import caffeine.world.Map;


public class MsgHandler {

  public static boolean handle(String line, Game game){

    System.out.println(line);
    Scanner lineScanner = new Scanner(line);

    while(lineScanner.hasNext()) {
      String word = lineScanner.next();
      char c = line.charAt(0);

      // Entity
      if (c == '#'){
        int id = lineScanner.nextInt();

        Entity e;
        if ((e = game.getEntity(id)) == null){
          e = new Entity(id);
          game.addEntity( e, e.getMapID());
        }

        word = lineScanner.next();
        c = word.charAt(0);
        if (c == 'X') {
          e.setX(lineScanner.nextInt());

          lineScanner.next(); // Y
          e.setY(lineScanner.nextInt());
        } else if (c == 'M') {
          Dir dir = Dir.valueOf(lineScanner.next());
          e.move(dir);
        } else if (c == 'J') {
          e.jump();
        }
        break;
      }

      // Map
      if (word.equals("M")){
        game.addMap(new Map(line));
        break;
      }
    }

    return true;
  }
}
