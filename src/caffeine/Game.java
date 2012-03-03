package caffeine;

import java.util.List;

public interface Game {
  
  void addPlayer(Player p);
  
  void createGUI();
  
  int numRoundsPlayed();
  
  void pause();
  
  void play();
  
  List<Player> players();
  
  void round();
}
