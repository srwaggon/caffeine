package caffeine.net.msg;

public class Query implements Msg {
  private String type;
  
  private Query() {
    
  }
  
  public String help() {
    return "get <World/Map/Entity> [<ID>]";
  }
  
  public String toString() {
    return "get " + type + " ";
  }
}
