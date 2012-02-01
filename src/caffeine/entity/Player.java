package caffeine.entity;

import caffeine.Server;
import caffeine.entity.brain.PlayerBrain;
import caffeine.world.Location;

public class Player extends Actor {

  public Player(){
    this(new Location());
  }
  public Player(Location loc){
    super(loc);
    Server.instance();
    brain = new PlayerBrain(Server.instance().interactionHandler());
    spriteID = 2;
  }
}
