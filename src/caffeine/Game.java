package caffeine;

import java.util.List;

import caffeine.view.GUI;

public interface Game {
  
  void addPlayer(Player p);
  
  GUI gui();
  
  int numRoundsPlayed();
  
  void pause();
  
  void play();
  
  List<Player> players();
  
  void round();
}
