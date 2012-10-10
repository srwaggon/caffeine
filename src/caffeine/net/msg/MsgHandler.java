package caffeine.net.msg;

import java.util.Scanner;

import caffeine.Game;
import caffeine.entity.PlayerEntity;
import caffeine.world.Dir;
import caffeine.world.Map;


public class MsgHandler {

  public static boolean handle(String line, Game game){
    Scanner lineScanner = new Scanner(line);

    PlayerEntity playerEntity;
    while(lineScanner.hasNext()) {
      String word = lineScanner.next();
      char c = line.charAt(0);

      // Entity
      if (c == '#'){
        int id = lineScanner.nextInt();

        if ((playerEntity = ((PlayerEntity) game.getEntity(id))) == null){
          playerEntity = new PlayerEntity(id);
          game.addEntity( playerEntity, playerEntity.getMapID());
        }

        word = lineScanner.next();
        c = word.charAt(0);
        if (c == 'X') {
          playerEntity.setX(lineScanner.nextInt());

          lineScanner.next(); // Y
          playerEntity.setY(lineScanner.nextInt());

          lineScanner.next(); // Z
          playerEntity.setZ(lineScanner.nextInt());

        } else if (c == 'M') {
          Dir dir = Dir.valueOf(lineScanner.next());
          playerEntity.move(dir);
        } else if (c == 'J') {
          playerEntity.jump();
        } else if (c == 'U') {
          playerEntity.useRightHand();
        }
        break;
      }

      // Map
      if (word.equals("M")){
        int id = lineScanner.nextInt();
        Map map;
        if ((map = game.getMap(id)) == null) {
          map = new Map(line);
          game.addMap(map);
        } else {
          //map.read(line);
        }
        break;
      }
    }

    return true;
  }
}
