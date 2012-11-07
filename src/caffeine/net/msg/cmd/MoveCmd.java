package caffeine.net.msg.cmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caffeine.Game;
import caffeine.world.Dir;

public class MoveCmd {
  protected static Pattern pattern = Pattern.compile("#\\s(\\d+)\\sM\\s([NESW])");

  public static boolean execute(String expr, Game game) {
    Matcher matcher = MoveCmd.pattern.matcher(expr);
    if (matcher.find()) {
      int id = Integer.parseInt(matcher.group(1));
      Dir dir = Dir.valueOf(matcher.group(2));

      game.getEntity(id).move(dir);

      return true;
    }
    return false;
  }

  public static boolean matches(String xpr) {
    return MoveCmd.pattern.matcher(xpr).find();
  }

  public static void main(String[] args) {
    System.out.println(MoveCmd.matches("# 12 M E"));
  }
}
