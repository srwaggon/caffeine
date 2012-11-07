package caffeine.net.msg;

import caffeine.Game;

public class Query extends CMD {
  private final String[] param;

  public Query(String request) {
    super(request);
    param = request.split(" ");
  }

  @Override
  public String help() {
    return "get <Class> [<ID>]";
  }

  public String[] param() {
    return param;
  }

  public String type() {
    return param[1];
  }

  @Override
  public String toString() {
    String result = "";
    for (String element : param) {
      result += element + " ";
    }
    return result;
  }

  @Override
  public boolean execute(String xpr, Game game) {
    // TODO Auto-generated method stub
    return false;
  }
}
