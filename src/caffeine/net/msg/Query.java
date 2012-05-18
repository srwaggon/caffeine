package caffeine.net.msg;

public class Query implements Msg {
  private String[] param;

  public Query(String request) {
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
}
