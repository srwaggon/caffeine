package caffeine;

import java.util.List;

import caffeine.view.GUI;

public interface Game {
  
  void addPlayer(Player p);
  
  void createGUI();
  
  GUI gui();
  
  int numRoundsPlayed();
  
  void pause();
  
  void play();
  
  List<Player> players();
  
  void round();
}
