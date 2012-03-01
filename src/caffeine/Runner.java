package caffeine;

import caffeine.entity.Player;
import caffeine.view.Screen;
import caffeine.world.Location;
import caffeine.world.Map;

public class Runner {
  
  /* Main method */
  public static void main(String args[]) {
    
    // Get the game
    Game game = Game.instance();
    
    // Add some data: A world, some entities
    Map map = new Map();
    game.world().add(map);
    
    game.createGUI();
    Location l = new Location(0, 48, 48);
    Player p1 = new Player(l, game.gui().getInteractions());
    map.add(p1);
    
    Screen s = game.gui().getContentPane();
    s.camera().focusOn(p1);
    
  }
  
}
