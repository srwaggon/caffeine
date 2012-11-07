package caffeine.net.msg;

import caffeine.Game;

public abstract class CMD {
  protected String helpString;
  protected String usage;

  public abstract boolean execute(String xpr, Game game);

  public boolean matches(String xpr) {
    return false;
  }

  public String help() {
    return helpString;
  }

  public String toString(){
    return getClass().toString() + ": " + usage;
  }
}
