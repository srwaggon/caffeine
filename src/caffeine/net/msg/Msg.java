package caffeine.net.msg;

import java.util.regex.Pattern;

// change to cmd?
public abstract class Msg {
  String helpString;
  String usage;
  Pattern pattern;

  public Msg(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }

  public boolean matches(String ok) {
    return pattern.matcher(ok).find();
  }

  public String help() {
    return helpString;
  }

  public String toString(){
    return getClass().toString() + ": " + usage;
  }
}
