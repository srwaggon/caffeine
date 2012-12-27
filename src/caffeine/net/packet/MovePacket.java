package caffeine.net.packet;

import caffeine.Game;
import caffeine.world.Dir;


public class MovePacket extends EventPacket {
  private static final long serialVersionUID = -2791171004223148512L;
  String username;
  Dir dir;

  public MovePacket(final String username, final Dir dir) {
    super(new Event() {
      private static final long serialVersionUID = 4850044753619438995L;

      public void apply() {
        Game game = Game.getInstance();
        game.getEntity(username).move(dir);
      }
    });
    this.username = username;
    this.dir = dir;
  }

  public String toString() {
    return String.format("MovePacket{%s, %s}", username, dir);
  }

}
